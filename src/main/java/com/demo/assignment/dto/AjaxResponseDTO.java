package com.demo.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AjaxResponseDTO {
    private List<NotificationDTO> notificationDTOS;
}
