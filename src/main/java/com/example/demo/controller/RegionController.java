package com.example.demo.controller;

import com.example.demo.model.entity.Region;
import com.example.demo.repo.RegionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@RestController
@RequestMapping("/api/region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionRepo regionRepo;

//    @PersistenceContext
//    private final EntityManager entityManager;

    @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Region> getAll(@RequestBody List<Integer> ids) {
        return regionRepo.findAllById(ids);
    }

    @GetMapping(value = "/get/{regionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Region getAll(@PathVariable Integer regionId) {
        Region region = regionRepo.findById(regionId).orElse(null);
        System.out.println(region);
        return region;
    }

//    public void cat() {
//        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("");
//        List<?> list = query.getResultList();
//        if (!list.isEmpty()) {
//
//        }
//    }

}
