package com.demo.assignment.repository;


import com.demo.assignment.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findById(Long id);


}
