package com.demo.assignment.dto;

import com.demo.assignment.model.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String mobilePhone;

    public Employee getEmployeeEntity() {
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
        employee.setMobilePhone(this.mobilePhone);
        return employee;
    }

    public EmployeeDTO(Employee employee) {
        this.setId(employee.getId());
        this.setFirstName(employee.getFirstName());
        this.setLastName(employee.getLastName());
        this.setMobilePhone(employee.getMobilePhone());
    }


    public String getFullName() {
        return firstName + " " + lastName;
    }

}
