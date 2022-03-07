package com.demo.assignment.service.impl;

import com.demo.assignment.dto.CustomerDTO;
import com.demo.assignment.dto.EmployeeDTO;
import com.demo.assignment.dto.NotificationDTO;
import com.demo.assignment.model.Customer;
import com.demo.assignment.model.Notification;
import com.demo.assignment.repository.CustomerRepository;
import com.demo.assignment.repository.NotificationRepository;
import com.demo.assignment.service.NotificationService;
import com.demo.assignment.utile.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@EnableAsync
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Notification saveNotification(EmployeeDTO employee, CustomerDTO customer, String body) {
        Notification notification = new Notification();
        notification.setBody(body);
        notification.setSendFrom(employee.getFullName());
        notification.setSendTo(customer.getFullName());
        notification.setCustomer(customer.getCustomerEntity());
        notification.setLocalDateTime(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream().map(NotificationDTO::new).collect(Collectors.toList());
    }

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    @Async
    public void scheduleFixedDelayTask() {
        for (Long customerId : Constants.CURRENT_CUSTOMERS_PROCESS.keySet()) {
            Customer customer = customerRepository.findById(customerId);
            NotificationDTO notificationDTO = Constants.CURRENT_CUSTOMERS_PROCESS.get(customerId);
            Notification notification = notificationDTO.getNotificationEntity(customer);
            notification.setLocalDateTime(LocalDateTime.now());
            notificationRepository.save(notification);
        }
    }


}
