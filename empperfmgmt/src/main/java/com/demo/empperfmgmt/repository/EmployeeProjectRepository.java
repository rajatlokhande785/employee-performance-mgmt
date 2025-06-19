package com.demo.empperfmgmt.repository;

import com.demo.empperfmgmt.entity.EmployeeProject;
import com.demo.empperfmgmt.entity.EmployeeProjectId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, EmployeeProjectId> {}
