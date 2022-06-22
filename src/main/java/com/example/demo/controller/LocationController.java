package com.example.demo.controller;

import com.example.demo.model.entity.Location;
import com.example.demo.repo.LocationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;
import static com.example.demo.repo.specification.LocationSpec.containsPostalCode;
import static com.example.demo.repo.specification.LocationSpec.hasStreetAddress;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationRepo locationRepo;

    @GetMapping
    public List<?> getAll() {
        return locationRepo.findAll();
    }

    @GetMapping("/temp/crt")
    public Page<Location> getAllCr(@RequestParam String streetAddress,
                                   @RequestParam String postalCode,
                                   @PageableDefault(size = 25) Pageable pageable) {
        return locationRepo.findAll(where(containsPostalCode(postalCode)).and(hasStreetAddress(streetAddress)), pageable);
    }

}
