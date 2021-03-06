package com.demo.assignment.controller;

import com.demo.assignment.dto.*;
import com.demo.assignment.model.Notification;
import com.demo.assignment.service.CustomerService;
import com.demo.assignment.service.EmployeeService;
import com.demo.assignment.service.NotificationService;
import com.demo.assignment.utile.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final Environment env;

    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final NotificationService notificationService;


    @GetMapping("/home")
    public String viewHomePage(Model model) {
        model.addAttribute("title", "Task Assignment");
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("notifications", notificationService.getAllNotifications());
        return "Home";
    }


    @PostMapping("/refresh")
    @ResponseBody
    public AjaxResponseDTO refreshNotificationBlock() {
        System.out.println("Refresh Called");
        return new AjaxResponseDTO(notificationService.getAllNotifications());
    }


    @PostMapping("/send")
    @ResponseBody
    public AjaxResponseDTO ajaxRequestSendNotification(@RequestBody AjaxRequestDTO ajaxRequestDTO) {
        EmployeeDTO employee = employeeService.getById(ajaxRequestDTO.getEmployeeId());
        CustomerDTO customer = customerService.getById(ajaxRequestDTO.getCustomerId());
        Notification notification = notificationService.saveNotification(employee, customer, (env.getProperty("demo.body.default1")));
        Constants.CURRENT_CUSTOMERS_PROCESS.remove(ajaxRequestDTO.getCustomerId());
        Constants.CURRENT_CUSTOMERS_PROCESS.put(customer.getId(), new NotificationTemplate(new NotificationDTO(notification), 2, LayerType.DEFAULT));
        return new AjaxResponseDTO(notificationService.getAllNotifications());
    }

    @PostMapping("/reply")
    @ResponseBody
    public AjaxResponseDTO ajaxRequestReplyNotificationDefault(@RequestBody AjaxRequestDTO ajaxRequestDTO) {
        CustomerDTO customer = customerService.getById(ajaxRequestDTO.getCustomerId());
        NotificationTemplate notificationTemplate = Constants.CURRENT_CUSTOMERS_PROCESS.get(customer.getId());
        NotificationDTO notificationDTO = notificationTemplate.getNotificationDTO();
        if (ajaxRequestDTO.getLayerType().equalsIgnoreCase(notificationTemplate.getLayerType().name())) {
            if (notificationDTO != null) {
                Constants.CURRENT_CUSTOMERS_PROCESS.remove(ajaxRequestDTO.getCustomerId());
                notificationDTO.setBody(env.getProperty("demo.reply." + notificationTemplate.getLayerType().name().toLowerCase()));
                notificationDTO.setLocalDateTime(LocalDateTime.now());
                Notification notification = notificationDTO.getNotificationEntity(customer.getCustomerEntity());
                notification.setId(null);
                notificationService.saveNotification(notification);
            }
        } else {
            Constants.CURRENT_CUSTOMERS_PROCESS.remove(ajaxRequestDTO.getCustomerId());
            notificationDTO.setLocalDateTime(LocalDateTime.now());
            notificationTemplate.setTemplateNumber(1);
            notificationTemplate.setLayerType(LayerType.valueOf(ajaxRequestDTO.getLayerType().toUpperCase()));
            notificationDTO.setBody(env.getProperty("demo.reply." + notificationTemplate.getLayerType().name().toLowerCase()));
            notificationDTO.setLocalDateTime(LocalDateTime.now());
            notificationTemplate.setNotificationDTO(notificationDTO);
            Constants.CURRENT_CUSTOMERS_PROCESS.put(ajaxRequestDTO.getCustomerId(), notificationTemplate);
            Notification notification = notificationDTO.getNotificationEntity(customer.getCustomerEntity());
            notification.setId(null);
            notificationService.saveNotification(notification);
        }

        return new AjaxResponseDTO(notificationService.getAllNotifications());
    }

}
