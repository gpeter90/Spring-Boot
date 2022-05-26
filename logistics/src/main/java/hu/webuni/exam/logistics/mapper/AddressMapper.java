package hu.webuni.exam.logistics.mapper;

import hu.webuni.exam.logistics.dto.AddressDto;
import hu.webuni.exam.logistics.model.Address;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    List<AddressDto> addressesToDtos(List<Address> addresses);

    AddressDto addresstoDto(Address address);

    Address dtoToAddress(AddressDto addressDto);
}
