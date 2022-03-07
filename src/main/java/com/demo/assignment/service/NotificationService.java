package com.demo.assignment.service;

import com.demo.assignment.dto.CustomerDTO;
import com.demo.assignment.dto.EmployeeDTO;
import com.demo.assignment.dto.NotificationDTO;
import com.demo.assignment.model.Notification;

import java.util.List;

public interface NotificationService {

    Notification saveNotification(EmployeeDTO employee, CustomerDTO customer, String body);

    Notification saveNotification(Notification notification);

    List<NotificationDTO> getAllNotifications();

    void scheduleFixedDelayTask();


}
