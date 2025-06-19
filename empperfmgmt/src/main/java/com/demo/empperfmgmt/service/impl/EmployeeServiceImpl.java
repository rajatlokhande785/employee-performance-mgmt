package com.demo.empperfmgmt.service.impl;

import com.demo.empperfmgmt.dto.EmployeeDetailDto;
import com.demo.empperfmgmt.dto.EmployeeDto;
import com.demo.empperfmgmt.entity.*;
import com.demo.empperfmgmt.exception.ResourceNotFoundException;
import com.demo.empperfmgmt.repository.EmployeeRepository;
import com.demo.empperfmgmt.repository.PerformanceReviewRepository;
import com.demo.empperfmgmt.service.EmployeeService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PerformanceReviewRepository reviewRepository;
    private final ModelMapper mapper;

    @Override
    public List<EmployeeDto> getEmployeesFiltered(Integer score, LocalDate reviewDate, List<String> departments, List<String> projects) {
        log.info("Fetching employees with filters - score: {}, reviewDate: {}, departments: {}, projects: {}",
                score, reviewDate, departments, projects);

        return employeeRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (departments != null && !departments.isEmpty()) {
                Join<Employee, Department> deptJoin = root.join("department");
                predicates.add(deptJoin.get("name").in(departments));
            }

            if (projects != null && !projects.isEmpty()) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<EmployeeProject> empProj = subquery.from(EmployeeProject.class);
                Join<EmployeeProject, Project> projJoin = empProj.join("project");
                subquery.select(empProj.get("employee").get("id"))
                        .where(projJoin.get("name").in(projects));

                predicates.add(root.get("id").in(subquery));
            }

            if (score != null && reviewDate != null) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<PerformanceReview> review = subquery.from(PerformanceReview.class);
                subquery.select(review.get("employee").get("id"))
                        .where(cb.equal(review.get("reviewDate"), reviewDate),
                                cb.equal(review.get("score"), score));

                predicates.add(root.get("id").in(subquery));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }).stream().map(e -> mapper.map(e, EmployeeDto.class)).toList();
    }

    @Override
    public EmployeeDetailDto getEmployeeDetails(Long id) {
        log.info("Fetching employee details for ID: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Employee not found for ID: {}", id);
                    return new ResourceNotFoundException("Employee not found with id " + id);
                });

        EmployeeDetailDto dto = mapper.map(employee, EmployeeDetailDto.class);
        dto.setRecentReviews(
                reviewRepository.findTop3ByEmployeeIdOrderByReviewDateDesc(id)
                        .stream().map(r -> mapper.map(r, EmployeeDetailDto.ReviewDto.class)).toList()
        );
        return dto;
    }
}
