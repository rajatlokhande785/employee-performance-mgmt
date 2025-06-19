package com.demo.empperfmgmt.repository;

import com.demo.empperfmgmt.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}
