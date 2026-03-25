package com.mallshop.modules.order.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private final Map<String, List<Map<String, Object>>> orderStore = new ConcurrentHashMap<>();

    public Map<String, Object> createOrder(String userId, List<Map<String, Object>> items, String address) {
        String orderNo = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Map<String, Object> order = Map.of(
                "orderNo", orderNo,
                "status", "CREATED",
                "address", address,
                "items", items,
                "createdAt", LocalDateTime.now().toString()
        );
        orderStore.computeIfAbsent(userId, key -> new ArrayList<>()).add(order);
        return order;
    }

    public List<Map<String, Object>> list(String userId) {
        return orderStore.getOrDefault(userId, new ArrayList<>());
    }
}
