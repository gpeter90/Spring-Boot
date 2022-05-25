package hu.webuni.exam.logistics.service;

import hu.webuni.exam.logistics.model.MileStone;
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

    @Transactional
    public TransportPlan addDelayToTransportPlan(TransportPlan transportPlan) {
        MileStone delayMilestone = transportPlan.getSection().getToMilestone();
        MileStone delayedMilestone = mileStoneService.addDelayToMileStone(delayMilestone);
        return null;
    }

}
