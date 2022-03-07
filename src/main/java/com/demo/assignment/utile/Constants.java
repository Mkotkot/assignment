package com.demo.assignment.utile;

import com.demo.assignment.dto.NotificationTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constants {

    public static Map<Long, NotificationTemplate> CURRENT_CUSTOMERS_PROCESS = new ConcurrentHashMap<>();
}
