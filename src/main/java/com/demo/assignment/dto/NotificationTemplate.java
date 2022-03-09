package com.demo.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationTemplate {
    private NotificationDTO notificationDTO;
    private int templateNumber;
    private LayerType layerType;

    public void increaseValueByOne() {
        this.templateNumber = this.templateNumber + 1;
    }

}
