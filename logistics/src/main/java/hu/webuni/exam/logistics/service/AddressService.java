package hu.webuni.exam.logistics.service;

import hu.webuni.exam.logistics.exception.NotValidAddressException;
import hu.webuni.exam.logistics.model.Address;
import hu.webuni.exam.logistics.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;
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
        if (ObjectUtils.isEmpty(address)) {
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

    public List<Address> findAddressByExample(Address example, String page, String size, String sort) {
        String city = example.getCity();
        String iso = example.getIso();
        String postcode = example.getPostcode();
        String street = example.getStreet();

        Specification<Address> spec = Specification.where(null);

        if (StringUtils.hasText(city)) {
            spec = spec.and(AddressSpecifications.hasCity(city));
        }

        if (StringUtils.hasText(street)) {
            spec = spec.and(AddressSpecifications.hasStreet(street));
        }

        if (StringUtils.hasText(iso)) {
            spec = spec.and(AddressSpecifications.hasIso(iso));
        }

        if (StringUtils.hasText(postcode)) {
            spec = spec.and(AddressSpecifications.hasPostCode(postcode));
        }

        if (StringUtils.hasText(page) ) {
            int p = Integer.parseInt(page);
            return (List<Address>) addressRepository.findAll(spec, Pageable.ofSize(p));
        }

        if (StringUtils.hasText(sort) ) {
            return addressRepository.findAll(spec, Sort.by(sort));
        } else {
            return addressRepository.findAll(spec, Sort.by("id"));
        }

    }
}
