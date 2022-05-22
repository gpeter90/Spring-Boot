package hu.webuni.exam.logistics.mapper;

import hu.webuni.exam.logistics.dto.AddressDto;
import hu.webuni.exam.logistics.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto addresstoDto(Address address);

    Address dtoToAddress(AddressDto addressDto);
}
