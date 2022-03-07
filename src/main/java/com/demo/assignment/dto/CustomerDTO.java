package com.demo.assignment.dto;

import com.demo.assignment.model.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String mobilePhone;

    public CustomerDTO(Customer customer) {
        this.setId(customer.getId());
        this.setFirstName(customer.getFirstName());
        this.setLastName(customer.getLastName());
        this.setMobilePhone(customer.getMobilePhone());
    }

    public Customer getCustomerEntity() {
        Customer customer = new Customer();
        customer.setId(this.id);
        customer.setFirstName(this.firstName);
        customer.setLastName(this.lastName);
        customer.setMobilePhone(this.mobilePhone);
        return customer;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
