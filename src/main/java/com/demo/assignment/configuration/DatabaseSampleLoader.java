package com.demo.assignment.configuration;

import com.demo.assignment.model.Customer;
import com.demo.assignment.model.Employee;
import com.demo.assignment.service.CustomerService;
import com.demo.assignment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class DatabaseSampleLoader {

    private final CustomerService customerService;

    private final EmployeeService employeeService;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        Employee e1 = new Employee();
        e1.setFirstName("Mohamed");
        e1.setLastName("Hamid");
        e1.setMobilePhone("+21212121212");

        Employee e2 = new Employee();
        e2.setFirstName("David");
        e2.setLastName("Smith");
        e2.setMobilePhone("+21212121212");

        Employee e3 = new Employee();
        e3.setFirstName("Sara");
        e3.setLastName("Alex");
        e3.setMobilePhone("+21212121212");

        employeeService.saveEmployee(e1);
        employeeService.saveEmployee(e2);
        employeeService.saveEmployee(e3);


        Customer c1 = new Customer();
        c1.setFirstName("Ali");
        c1.setLastName("Ahmed");
        c1.setMobilePhone("+21212121212");

        Customer c2 = new Customer();
        c2.setFirstName("Sofia");
        c2.setLastName("Hans");
        c2.setMobilePhone("+21212121212");

        Customer c3 = new Customer();
        c3.setFirstName("Jay");
        c3.setLastName("Tom");
        c3.setMobilePhone("+21212121212");

        customerService.saveCustomer(c1);
        customerService.saveCustomer(c2);
        customerService.saveCustomer(c3);


    }
}
