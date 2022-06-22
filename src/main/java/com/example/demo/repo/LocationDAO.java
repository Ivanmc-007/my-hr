package com.example.demo.repo;

import com.example.demo.model.entity.Location;
import com.example.demo.model.entity.Location_;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Deprecated
@Repository
@RequiredArgsConstructor
public class LocationDAO {

    private final EntityManager em;

    @Deprecated
    public List<Location> findRegionsByRegionName(String streetAddress, String postalCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Location> cq = cb.createQuery(Location.class);

        Root<Location> root = cq.from(Location.class);
        Predicate streetAddressPrd = cb.equal(root.get(Location_.STREET_ADDRESS), streetAddress);
        Predicate postalCodePrd = cb.like(root.get(Location_.POSTAL_CODE), "%" + postalCode + "%");

        cq.where(streetAddressPrd, postalCodePrd);

        TypedQuery<Location> query = em.createQuery(cq);
        return query.getResultList();
    }

}
