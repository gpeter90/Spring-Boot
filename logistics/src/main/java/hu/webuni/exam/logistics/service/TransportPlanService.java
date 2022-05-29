package hu.webuni.exam.logistics.service;

import hu.webuni.exam.logistics.exception.NoValidMileStoneToTranportPlanException;
import hu.webuni.exam.logistics.model.MileStone;
import hu.webuni.exam.logistics.model.Section;
import hu.webuni.exam.logistics.model.TransportPlan;
import hu.webuni.exam.logistics.repository.TransportPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransportPlanService {

    @Value("${transport.delay.decrease.low.percent}")
    private double lowIncomeDecrease;

    @Value("${transport.delay.decrease.medium.percent}")
    private double mediumIncomeDecrease;

    @Value("${transport.delay.decrease.high.percent}")
    private double highIncomeDecrease;

    @Autowired
    TransportPlanRepository transportPlanRepository;

    @Autowired
    MileStoneService mileStoneService;

    @Autowired
    SectionService sectionService;

    @Transactional
    public TransportPlan addDelayToTransportPlan(TransportPlan transportPlan) {

        if (transportPlanRepository.existsById(transportPlan.getId())) {

            if (sectionService.isSectionExistsById(transportPlan.getSection().getId())) {

                Optional<TransportPlan> originalPlan = transportPlanRepository.findById(transportPlan.getId());

                if (originalPlan.get().getSection().getToMilestone().getId() == transportPlan.getSection().getToMilestone().getId()
                        && originalPlan.get().getSection().getFromMilestone().getId() == transportPlan.getSection().getFromMilestone().getId()) {

                    transportPlan = checkFromMilestoneChanges(transportPlan);

                    MileStone toMilestone = mileStoneService.updateMilestone(transportPlan.getSection().getToMilestone());

                    MileStone fromMilestone = mileStoneService.updateMilestone(transportPlan.getSection().getFromMilestone());

                    transportPlan.getSection().setFromMilestone(fromMilestone);
                    transportPlan.getSection().setToMilestone(toMilestone);

                    transportPlan.setSection(sectionService.addNewSection(transportPlan.getSection()));

                    return transportPlanRepository.save(transportPlan);
                }

                throw new NoValidMileStoneToTranportPlanException();
            }
        }

        throw new NoSuchElementException();
    }

    private TransportPlan checkFromMilestoneChanges(TransportPlan transportPlan) {
        if (isFromMilestoneChanged(transportPlan)) {

            Section section = transportPlan.getSection();
            MileStone toMileStone = section.getToMilestone();

            LocalDateTime timeToBeChanged = transportPlan.getSection().getToMilestone().getPlannedTime();
            LocalDateTime originalTime = mileStoneService.findMilestoneById(
                    transportPlan
                            .getSection()
                            .getFromMilestone().getId()
            ).get().getPlannedTime();
            LocalDateTime delayedTime = transportPlan.getSection().getFromMilestone().getPlannedTime();

            long delayinMinutes = ChronoUnit.MINUTES.between(originalTime, delayedTime);

            toMileStone.setPlannedTime(timeToBeChanged.plusMinutes(delayinMinutes));
            section.setToMilestone(toMileStone);

            transportPlan.setSection(section);
        }
        return calculateIncomeDecrease(transportPlan);
    }

    private boolean isFromMilestoneChanged(TransportPlan transportPlan) {
        LocalDateTime originalTime = mileStoneService.findMilestoneById(
                transportPlan
                        .getSection()
                        .getFromMilestone().getId()
        ).get().getPlannedTime();

        LocalDateTime changedTime = transportPlan.getSection().getFromMilestone().getPlannedTime();

        long delay = ChronoUnit.MINUTES.between(originalTime, changedTime);

        return delay > 0;
    }

    private TransportPlan calculateIncomeDecrease(TransportPlan transportPlan) {

        LocalDateTime plannedTime = mileStoneService.findMilestoneById(
                transportPlan
                        .getSection()
                        .getToMilestone().getId()
                ).get().getPlannedTime();

        LocalDateTime delayedTime = transportPlan.getSection().getToMilestone().getPlannedTime();

        Optional<TransportPlan> originalTransportPlan = transportPlanRepository.findById(transportPlan.getId());
        long delayInMinutes = ChronoUnit.MINUTES.between(plannedTime, delayedTime);

        if (30 <= delayInMinutes && delayInMinutes < 60) {
            long originalIncome = originalTransportPlan.get().getIncome();
            transportPlan.setIncome((long) (originalIncome - (originalIncome * (lowIncomeDecrease / 100))));
        }

        if (60 <= delayInMinutes && delayInMinutes < 120) {
            long originalIncome = originalTransportPlan.get().getIncome();
            transportPlan.setIncome((long) (originalIncome - (originalIncome * (mediumIncomeDecrease / 100))));
        }

        if (120 < delayInMinutes) {
            long originalIncome = originalTransportPlan.get().getIncome();
            transportPlan.setIncome((long) (originalIncome - (originalIncome * (highIncomeDecrease / 100))));
        }

        return transportPlan;
    }

}
