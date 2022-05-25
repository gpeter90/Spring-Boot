package hu.webuni.exam.logistics.mapper;

import hu.webuni.exam.logistics.dto.TransportPlanDto;
import hu.webuni.exam.logistics.model.TransportPlan;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {

    List<TransportPlanDto> TransportPlansToDtos(List<TransportPlan> transportPlans);

    TransportPlanDto TransportPlantoDto(TransportPlan transportPlan);

    TransportPlan dtoToTransportPlan(TransportPlanDto transportPlanDto);
}
