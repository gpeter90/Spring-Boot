package hu.webuni.exam.logistics.web;

import hu.webuni.exam.logistics.dto.TransportPlanDto;
import hu.webuni.exam.logistics.mapper.TransportPlanMapper;
import hu.webuni.exam.logistics.model.TransportPlan;
import hu.webuni.exam.logistics.service.TransportPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transportPlans")
public class TranportPlanController {

    @Autowired
    TransportPlanService transportPlanService;

    @Autowired
    TransportPlanMapper transportPlanMapper;

    @PostMapping
    public TransportPlanDto addNewTransportPlan(@RequestBody TransportPlanDto transportPlanDto) {
        TransportPlan transportPlan = transportPlanMapper.dtoToTransportPlan(transportPlanDto);
        return transportPlanMapper.TransportPlantoDto(transportPlanService.addNewTransportPlan(transportPlan));
    }

    @PutMapping("/{id}/delay")
    public TransportPlanDto addDelayToTransportPlan(@PathVariable long id, @RequestBody TransportPlanDto transportPlanDto) {
        TransportPlan transportPlan = transportPlanMapper.dtoToTransportPlan(transportPlanDto);
        transportPlan.setId(id);
        return transportPlanMapper.TransportPlantoDto(transportPlanService.addDelayToTransportPlan(transportPlan));
    }
}
