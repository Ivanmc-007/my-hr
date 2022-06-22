package com.example.demo.repo;

import com.example.demo.model.entity.Employee;
import com.example.demo.model.entity.Employee_;
import com.example.demo.repo.criteria.EmployeeSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Page<Employee> findAllWithFilters(Pageable pageable,
                                             EmployeeSearchCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        // из какой сущности мы хотим получить результат
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        // Создаём предикат, который будет фильтровать наш результат
        Predicate predicate = this.getPredicate(searchCriteria, employeeRoot, criteriaBuilder);
        criteriaQuery.where(predicate);
        this.setOrder(pageable, criteriaQuery, employeeRoot, criteriaBuilder);
        TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        long employeesCount = getEmployeesCount(predicate, criteriaBuilder);
        return new PageImpl<>(typedQuery.getResultList(), pageable, employeesCount);
    }

    private Predicate getPredicate(EmployeeSearchCriteria searchCriteria,
                                   Root<Employee> employeeRoot, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(searchCriteria.getFirstName())) {
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.upper(employeeRoot.get(Employee_.FIRST_NAME)), searchCriteria.getFirstName().toUpperCase() + "%"
                    )
            );
        }
        if (Objects.nonNull(searchCriteria.getLastName())) {
            predicates.add(
                    criteriaBuilder.like(
                            // ignore Upper(Lower)case
                            criteriaBuilder.upper(employeeRoot.get(Employee_.LAST_NAME)), searchCriteria.getLastName().toUpperCase() + "%"
                    )
            );

        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(Pageable pageable, CriteriaQuery<Employee> criteriaQuery, Root<Employee> employeeRoot, CriteriaBuilder criteriaBuilder) {
        criteriaQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), employeeRoot, criteriaBuilder));
    }

    private long getEmployeesCount(Predicate predicate, CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> countRoot = countQuery.from(Employee.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    public Employee insertWithTransactional(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

}
