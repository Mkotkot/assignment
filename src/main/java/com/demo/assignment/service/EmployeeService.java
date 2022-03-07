package com.demo.assignment.service;

import com.demo.assignment.dto.EmployeeDTO;
import com.demo.assignment.model.Employee;

import java.util.List;

public interface EmployeeService {

    void saveEmployee(Employee employee);


    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getById(Long id );
}
