package hu.webuni.exam.logistics.service;

import hu.webuni.exam.logistics.exception.NotValidAddressException;
import hu.webuni.exam.logistics.model.Address;
import hu.webuni.exam.logistics.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(long id) {
        return addressRepository.findById(id);
    }

    @Transactional
    public Address saveAddress(Address address) {
        checkAddressIsValid(address);
        return addressRepository.save(address);
    }

    @Transactional
    public void deleteAddress(long id) {
        addressRepository.deleteById(id);
    }

    private void checkAddressIsValid(Address address) {
        if (Objects.isNull(address)) {
            throw new NotValidAddressException();
        }
    }

    public Address updateAddress(Address address) {
        if (addressRepository.existsById(address.getId())) {
            return addressRepository.save(address);
        } else {
            throw new NoSuchElementException();
        }
    }
}
