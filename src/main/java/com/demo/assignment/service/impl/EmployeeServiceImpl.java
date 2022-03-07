package com.demo.assignment.service.impl;

import com.demo.assignment.dto.EmployeeDTO;
import com.demo.assignment.model.Employee;
import com.demo.assignment.repository.EmployeeRepository;
import com.demo.assignment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeDTO::new).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getById(Long id) {
        return new EmployeeDTO(employeeRepository.findById(id));
    }
}