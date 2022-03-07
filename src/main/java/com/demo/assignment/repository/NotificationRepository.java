package com.demo.assignment.repository;

import com.demo.assignment.model.Customer;
import com.demo.assignment.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findAllByCustomer(Customer customer);

}
