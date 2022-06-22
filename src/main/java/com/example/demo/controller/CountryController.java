package com.example.demo.controller;

import com.example.demo.mapper.CountryMapper;
import com.example.demo.model.dto.CountryDTO;
import com.example.demo.model.entity.Country;
import com.example.demo.repo.CountryRepo;
import com.example.demo.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryRepo countryRepo;

    private final CountryService countryService;

    private final CountryMapper countryMapper;

    @Operation(description = "Get list countries by countryIds",
            responses = {@ApiResponse(responseCode = "200", description = "OK")})
    @GetMapping(value = "/get/ids", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> getAllById(@RequestParam Set<String> countryIds) {
        return countryRepo.findAllById(countryIds);
    }

    @Operation(description = "Get list countries by regionId",
            responses = {@ApiResponse(responseCode = "200", description = "OK")})
    @GetMapping(value = "/get/sort", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> getByRegionId(@RequestParam Integer regionId, @RequestParam(required = false) String srtFldName) {
        return countryService.getByRegionId(regionId, srtFldName);
    }

    @GetMapping(value = "/cat")
    public List<CountryDTO> findListCountry() {
        return countryRepo.findListCountry().stream()
                .map(countryMapper::toCountryDTO)
                .collect(Collectors.toList());
    }

}
