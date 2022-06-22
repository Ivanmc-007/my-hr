package com.example.demo.controller;

import com.example.demo.model.entity.Department;
import com.example.demo.repo.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepo departmentRepo;

    @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Department> getAll(@RequestBody List<Integer> ids) {
        return departmentRepo.findAllById(ids);
    }

}
