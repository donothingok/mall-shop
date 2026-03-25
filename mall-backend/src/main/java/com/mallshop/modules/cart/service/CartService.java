package com.mallshop.modules.cart.service;

import com.mallshop.modules.cart.dto.AddCartItemRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CartService {

    private final Map<String, List<Map<String, Object>>> cartStore = new ConcurrentHashMap<>();

    public List<Map<String, Object>> list(String userId) {
        return cartStore.getOrDefault(userId, new ArrayList<>());
    }

    public void add(String userId, AddCartItemRequest req) {
        List<Map<String, Object>> items = cartStore.computeIfAbsent(userId, key -> new ArrayList<>());
        items.add(Map.of("productId", req.getProductId(), "quantity", req.getQuantity()));
    }

    public void clear(String userId) {
        cartStore.remove(userId);
    }
}
