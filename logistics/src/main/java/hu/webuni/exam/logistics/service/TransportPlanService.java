package hu.webuni.exam.logistics.service;

import hu.webuni.exam.logistics.model.MileStone;
import hu.webuni.exam.logistics.model.Section;
import hu.webuni.exam.logistics.model.TransportPlan;
import hu.webuni.exam.logistics.repository.TransportPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransportPlanService {

    @Autowired
    TransportPlanRepository transportPlanRepository;

    @Autowired
    MileStoneService mileStoneService;

    @Autowired
    SectionService sectionService;

    @Transactional
    public TransportPlan addNewTransportPlan(TransportPlan transportPlan) {
        Section section = transportPlan.getSection();

        MileStone fromMilestone = section.getFromMilestone();
        MileStone toMilestone = section.getToMilestone();

        fromMilestone = mileStoneService.addNewMilestone(fromMilestone);
        toMilestone = mileStoneService.addNewMilestone(toMilestone);

        section.setFromMilestone(fromMilestone);
        section.setToMilestone(toMilestone);

        section = sectionService.addNewSection(section);

        transportPlan.setSection(section);

        return transportPlanRepository.save(transportPlan);
    }

    @Transactional
    public TransportPlan addDelayToTransportPlan(TransportPlan transportPlan) {
        if (transportPlanRepository.existsById(transportPlan.getId())) {
            Section section = transportPlan.getSection();

            MileStone fromMilestone = section.getFromMilestone();
            MileStone toMilestone = section.getToMilestone();

            fromMilestone = mileStoneService.addNewMilestone(fromMilestone);
            toMilestone = mileStoneService.addNewMilestone(toMilestone);

            section.setFromMilestone(fromMilestone);
            section.setToMilestone(toMilestone);

            section = sectionService.addNewSection(section);

            transportPlan.setSection(section);

            return transportPlanRepository.save(transportPlan);
        }
        return null;
    }

}
