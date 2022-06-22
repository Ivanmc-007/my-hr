package com.example.demo.repo;

import com.example.demo.model.dto.EmployeeGetDTO;
import com.example.demo.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    @Query(nativeQuery = true)
    List<EmployeeGetDTO> findAllIdNQ(List<Integer> ids);

}
