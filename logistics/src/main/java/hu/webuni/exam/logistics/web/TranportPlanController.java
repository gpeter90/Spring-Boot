package hu.webuni.exam.logistics.web;

import hu.webuni.exam.logistics.dto.TransportPlanDto;
import hu.webuni.exam.logistics.mapper.TransportPlanMapper;
import hu.webuni.exam.logistics.model.TransportPlan;
import hu.webuni.exam.logistics.service.TransportPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/transportPlans")
public class TranportPlanController {

    @Autowired
    TransportPlanService transportPlanService;

    @Autowired
    TransportPlanMapper transportPlanMapper;

    @PostMapping("/{id}/delay")
    public TransportPlanDto addDelayToTransportPlan(@PathVariable long id, @RequestBody TransportPlanDto transportPlanDto) {
        TransportPlan transportPlan = transportPlanMapper.dtoToTransportPlan(transportPlanDto);
        transportPlan.setId(id);
        try {
            return transportPlanMapper.TransportPlantoDto(transportPlanService.addDelayToTransportPlan(transportPlan));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
