package com.demo.assignment.service;

import com.demo.assignment.dto.CustomerDTO;
import com.demo.assignment.model.Customer;

import java.util.List;

public interface CustomerService {
    void saveCustomer(Customer customer);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getById(Long id);

}
