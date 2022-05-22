package hu.webuni.exam.logistics.service;

import hu.webuni.exam.logistics.model.Address;
import org.springframework.data.jpa.domain.Specification;

public class AddressSpecifications {

    public static Specification<Address> hasCity(String city) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("city"), city + "%"));
    }

    public static Specification<Address> hasStreet(String street) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("street"), street + "%"));
    }

    public static Specification<Address> hasIso(String iso) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("iso"), iso));
    }

    public static Specification<Address> hasPostCode(String postCode) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("postcode"), postCode));
    }
}
