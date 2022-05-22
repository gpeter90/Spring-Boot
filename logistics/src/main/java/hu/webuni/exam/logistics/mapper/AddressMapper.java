package hu.webuni.exam.logistics.mapper;

import hu.webuni.exam.logistics.dto.AddressDto;
import hu.webuni.exam.logistics.model.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    List<AddressDto> airportsToDtos(List<Address> airports);

    AddressDto addresstoDto(Address address);

    Address dtoToAddress(AddressDto addressDto);
}
