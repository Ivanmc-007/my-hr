package com.example.demo.controller;

import com.example.demo.model.dto.EmployeeGetDTO;
import com.example.demo.model.dto.EmployeeSaveDTO;
import com.example.demo.model.entity.Employee;
import com.example.demo.model.entity.Employee_;
import com.example.demo.repo.criteria.EmployeeSearchCriteria;
import com.example.demo.service.EmployeeService;
import com.example.demo.util.HttpStatus;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @ApiResponses({
            @ApiResponse(responseCode = HttpStatus.OK, description = "OK")
    })
    @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeGetDTO> getAll(@RequestBody List<Integer> ids) {
        return employeeService.findAllById(ids);
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Employee> findAllWithFilters(@PageableDefault(size = 25, sort = {Employee_.LAST_NAME, Employee_.FIRST_NAME}, direction = Sort.Direction.ASC) Pageable pageable,
                                             EmployeeSearchCriteria searchCriteria) {
        return employeeService.findAllWithFilters(pageable, searchCriteria);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee save(@RequestBody EmployeeSaveDTO employeeDTO) {
        return employeeService.insert(employeeDTO);
    }

}
