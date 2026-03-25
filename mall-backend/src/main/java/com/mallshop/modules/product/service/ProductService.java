package com.mallshop.modules.product.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {

    private final Map<Long, Map<String, Object>> productStore = new ConcurrentHashMap<>();

    public ProductService() {
        productStore.put(1L, Map.of("id", 1L, "name", "示例商品A", "price", new BigDecimal("99.00"), "stock", 120));
        productStore.put(2L, Map.of("id", 2L, "name", "示例商品B", "price", new BigDecimal("199.00"), "stock", 80));
    }

    public List<Map<String, Object>> list() {
        return productStore.values().stream().toList();
    }

    public Map<String, Object> getById(Long id) {
        return productStore.get(id);
    }
}
