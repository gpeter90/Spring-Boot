package hu.webuni.exam.logistics.web;

import hu.webuni.exam.logistics.dto.AddressDto;
import hu.webuni.exam.logistics.mapper.AddressMapper;
import hu.webuni.exam.logistics.model.Address;
import hu.webuni.exam.logistics.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    AddressMapper addressMapper;

    @PostMapping
    public AddressDto createAddress(@RequestBody AddressDto addressDto) {
        Address address = addressService.saveAddress(addressMapper.dtoToAddress(addressDto));
        return addressMapper.addresstoDto(address);
    }
}
