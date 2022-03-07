package com.demo.assignment.utile;

import com.demo.assignment.dto.NotificationDTO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constants {

    public static Map<Long, NotificationDTO> CURRENT_CUSTOMERS_PROCESS = new ConcurrentHashMap<>();
}
