package com.example.demo.service;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.dto.EmployeeGetDTO;
import com.example.demo.model.dto.EmployeeSaveDTO;
import com.example.demo.model.entity.Employee;
import com.example.demo.repo.EmployeeDAO;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.criteria.EmployeeSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeDAO employeeDAO;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeGetDTO> findAllById(List<Integer> ids) {
        return employeeRepo.findAllIdNQ(ids);
    }

    public Page<Employee> findAllWithFilters(Pageable pageable, EmployeeSearchCriteria searchCriteria) {
        return employeeDAO.findAllWithFilters(pageable, searchCriteria);
    }

    @Transactional
    public Employee insert(EmployeeSaveDTO employeeDTO) {
        Employee employee = employeeMapper.toEmployee(employeeDTO);
        return employeeDAO.insertWithTransactional(employee);
    }

}
