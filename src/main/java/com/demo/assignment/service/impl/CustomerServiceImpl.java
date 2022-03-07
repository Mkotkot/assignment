package com.demo.assignment.service.impl;

import com.demo.assignment.dto.CustomerDTO;
import com.demo.assignment.model.Customer;
import com.demo.assignment.repository.CustomerRepository;
import com.demo.assignment.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerDTO::new).collect(Collectors.toList());

    }

    @Override
    public CustomerDTO getById(Long id) {
        return new CustomerDTO(customerRepository.findById(id));
    }

}
