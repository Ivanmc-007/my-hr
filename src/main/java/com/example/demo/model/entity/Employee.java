package com.example.demo.model.entity;

import com.example.demo.model.dto.EmployeeGetDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@NamedNativeQueries(
        @NamedNativeQuery(name = "Employee.findAllIdNQ",
                query = "select \n" +
                        "    emp.employee_id,\n" +
                        "    emp.first_name, \n" +
                        "    emp.last_name,\n" +
                        "    emp.email,\n" +
                        "    emp.phone_number,\n" +
                        "    emp.hire_date,\n" +
                        "    emp.job_id,\n" +
                        "    emp.salary,\n" +
                        "    emp.commission_pct,\n" +
                        "    emp.manager_id,\n" +
                        "    emp.department_id\n" +
                        "from EMPLOYEES emp where emp.EMPLOYEE_ID IN :ids",
                resultSetMapping = "EmployeeGetDTOMapper"
        )
)
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "EmployeeGetDTOMapper",
                classes = @ConstructorResult(
                        targetClass = EmployeeGetDTO.class,
                        columns = {
                                @ColumnResult(name = "employee_id", type = Integer.class),
                                @ColumnResult(name = "first_name", type = String.class),
                                @ColumnResult(name = "last_name", type = String.class),
                                @ColumnResult(name = "email", type = String.class),
                                @ColumnResult(name = "phone_number", type = String.class),
                                @ColumnResult(name = "hire_date", type = LocalDate.class),
                                @ColumnResult(name = "job_id", type = String.class),
                                @ColumnResult(name = "salary", type = BigDecimal.class),
                                @ColumnResult(name = "commission_pct", type = BigDecimal.class),
                                @ColumnResult(name = "manager_id", type = Integer.class),
                                @ColumnResult(name = "department_id", type = Integer.class),
                        }))
})
@Data
@Entity
@Table(name = "EMPLOYEES")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEES_SEQ")
    @SequenceGenerator(name = "EMPLOYEES_SEQ", sequenceName = "EMPLOYEES_SEQ", allocationSize = 1)
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "commission_pct")
    private BigDecimal commissionPct;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    private Employee manager;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;

}
