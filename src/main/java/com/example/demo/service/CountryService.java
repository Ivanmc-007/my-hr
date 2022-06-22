package com.example.demo.service;

import com.example.demo.model.entity.Country;
import com.example.demo.repo.CountryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepo countryRepo;

    public List<Country> getByRegionId(Integer regionId, String srtFldName) {
        try {
            return srtFldName == null ? countryRepo.findByRegionId(regionId) :
                    countryRepo.findByRegionId(regionId, Sort.by(Sort.Direction.ASC, srtFldName));
        } catch (PropertyReferenceException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

}
