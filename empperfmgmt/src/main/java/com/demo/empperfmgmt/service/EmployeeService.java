package com.demo.empperfmgmt.service;

import com.demo.empperfmgmt.dto.EmployeeDetailDto;
import com.demo.empperfmgmt.dto.EmployeeDto;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getEmployeesFiltered(Integer score, LocalDate reviewDate, List<String> departments, List<String> projects);
    EmployeeDetailDto getEmployeeDetails(Long id);
}
