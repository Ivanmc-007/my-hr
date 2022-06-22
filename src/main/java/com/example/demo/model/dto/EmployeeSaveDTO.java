package com.example.demo.model.dto;

import com.example.demo.model.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeSaveDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate hireDate;

    private String jobId;

    private BigDecimal salary;

    private BigDecimal commissionPct;

    private Integer managerId;

    private Integer departmentId;

}
