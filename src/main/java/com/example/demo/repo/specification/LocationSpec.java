package com.example.demo.repo.specification;

import com.example.demo.model.entity.Location;
import com.example.demo.model.entity.Location_;
import org.springframework.data.jpa.domain.Specification;

public interface LocationSpec {

    static Specification<Location> hasStreetAddress(String streetAddress) {
        return (location, cq, cb) -> cb.equal(location.get(Location_.STREET_ADDRESS), streetAddress);
    }

    static Specification<Location> containsPostalCode(String postalCode) {
        return (location, cq, cb) -> cb.like(location.get(Location_.POSTAL_CODE), "%" + postalCode + "%");
    }

}
