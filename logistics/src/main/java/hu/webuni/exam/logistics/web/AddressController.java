package hu.webuni.exam.logistics.web;

import hu.webuni.exam.logistics.dto.AddressDto;
import hu.webuni.exam.logistics.mapper.AddressMapper;
import hu.webuni.exam.logistics.model.Address;
import hu.webuni.exam.logistics.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    AddressMapper addressMapper;

    @GetMapping
    public List<AddressDto> getAll() {
        return addressMapper.airportsToDtos(addressService.findAll());
    }

    @GetMapping("/{id}")
    public AddressDto getAddressById(@PathVariable long id) {
        Address address = addressService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return addressMapper.addresstoDto(address);
    }

    @PostMapping
    public AddressDto createAddress(@RequestBody @NonNull AddressDto addressDto) {
        Address address = addressService.saveAddress(addressMapper.dtoToAddress(addressDto));
        return addressMapper.addresstoDto(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> modifyAddress(@PathVariable long id, @RequestBody AddressDto addressDto) {
        Address address = addressMapper.dtoToAddress(addressDto);
        address.setId(id);
        try {
            AddressDto savedAirportDto = addressMapper.addresstoDto(addressService.updateAddress(address));
            return ResponseEntity.ok(savedAirportDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable long id) {
        addressService.deleteAddress(id);
    }

}
