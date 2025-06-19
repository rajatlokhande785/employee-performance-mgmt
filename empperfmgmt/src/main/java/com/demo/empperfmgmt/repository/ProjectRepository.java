package com.demo.empperfmgmt.repository;

import com.demo.empperfmgmt.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {}
