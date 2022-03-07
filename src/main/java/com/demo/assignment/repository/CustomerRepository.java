package com.demo.assignment.repository;

import com.demo.assignment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findById(Long id);
}
