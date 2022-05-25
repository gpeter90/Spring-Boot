package hu.webuni.exam.logistics.web;

import hu.webuni.exam.logistics.dto.TransportPlanDto;
import hu.webuni.exam.logistics.mapper.TransportPlanMapper;
import hu.webuni.exam.logistics.model.TransportPlan;
import hu.webuni.exam.logistics.service.TransportPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transportPlans")
public class TranportPlanController {

    @Autowired
    TransportPlanService transportPlanService;

    @Autowired
    TransportPlanMapper transportPlanMapper;

    @PostMapping("/{id}/delay")
    public TransportPlanDto addDelayToTransportPlan(@RequestBody TransportPlanDto transportPlanDto) {
        TransportPlan transportPlan = transportPlanService.addDelayToTransportPlan(transportPlanMapper.dtoToTransportPlan(transportPlanDto));
        return transportPlanMapper.TransportPlantoDto(transportPlan);
    }
}
