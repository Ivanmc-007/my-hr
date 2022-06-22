package com.example.demo.repo;

import com.example.demo.model.dto.CountryDTOInt;
import com.example.demo.model.entity.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepo extends JpaRepository<Country, String> {

    List<Country> findByRegionId(Integer regionId);

    List<Country> findByRegionId(Integer regionId, Sort sort);

    @Query(value = "SELECT country_id id, country_name countryName FROM countries", nativeQuery = true)
    List<CountryDTOInt> findListCountry();

}
