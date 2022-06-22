package com.example.demo.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeGetDTO {

    @Schema(description = "ID работника")
    private Integer id;

    @Schema(description = "Имя работника")
    private String firstName;

    @Schema(description = "Фамилия работника")
    private String lastName;

    @Schema(description = "Почта работника")
    private String email;

    @Schema(description = "Телефон работника")
    private String phoneNumber;

    @Schema(description = "Дата приема на работу")
    private LocalDate hireDate;

    @Schema(description = "ID работы")
    private String jobId;

    @Schema(description = "Зарплата")
    private BigDecimal salary;

    @Schema(description = "Комиссионные")
    private BigDecimal commissionPct;

    @Schema(description = "ID менеджера")
    private Integer managerId;

    @Schema(description = "ID отдела")
    private Integer departmentId;

}
