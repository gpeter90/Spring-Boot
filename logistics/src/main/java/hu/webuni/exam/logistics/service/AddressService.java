package hu.webuni.exam.logistics.service;

import hu.webuni.exam.logistics.exception.NotValidAddressException;
import hu.webuni.exam.logistics.model.Address;
import hu.webuni.exam.logistics.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address saveAddress(Address address) {
        checkAddressIsValid(address);
        return addressRepository.save(address);
    }

    private void checkAddressIsValid(Address address) {
        if (Objects.isNull(address)) {
            throw new NotValidAddressException();
        }
    }
}
