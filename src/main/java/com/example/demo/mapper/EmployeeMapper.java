package com.example.demo.mapper;

import com.example.demo.model.dto.EmployeeSaveDTO;
import com.example.demo.model.entity.Department;
import com.example.demo.model.entity.Employee;
import com.example.demo.model.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    @Mappings({
            @Mapping(source = "employeeSaveDTO.managerId", target = "manager", qualifiedByName = "getManagerId"),
            @Mapping(source = "employeeSaveDTO.departmentId", target = "department", qualifiedByName = "getDepartmentId"),
            @Mapping(source = "employeeSaveDTO.jobId", target = "job", qualifiedByName = "getJobId")
    })
    public abstract Employee toEmployee(EmployeeSaveDTO employeeSaveDTO);

    @Named("getManagerId")
    public Employee getManagerId(Integer managerId) {
        return Employee.builder().id(managerId).build();
    }

    @Named("getDepartmentId")
    public Department getDepartmentId(Integer departmentId) {
        return Department.builder().id(departmentId).build();
    }

    @Named("getJobId")
    public Job getJobId(String jobId) {
        return Job.builder().id(jobId).build();
    }

}
