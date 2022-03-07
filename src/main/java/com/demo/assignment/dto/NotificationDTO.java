package com.demo.assignment.dto;

import com.demo.assignment.model.Customer;
import com.demo.assignment.model.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class NotificationDTO {

    private Long id;
    private String from;
    private String to;
    private String body;
    private LocalDateTime localDateTime;
    private Long customerId;

    public NotificationDTO(Notification notification) {
        this.id = notification.getId();
        this.body = notification.getBody();
        this.from = notification.getSendFrom();
        this.to = notification.getSendTo();
        this.localDateTime = notification.getLocalDateTime();
        this.customerId = notification.getCustomer().getId();
    }

    public Notification getNotificationEntity(Customer customer) {
        Notification notification = new Notification();
        notification.setCustomer(customer);
        notification.setSendTo(this.to);
        notification.setSendFrom(this.from);
        notification.setBody(this.body);
        notification.setLocalDateTime(this.localDateTime);
        return notification;
    }


}
