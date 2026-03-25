package com.mallshop.modules.product.service;

import com.mallshop.modules.product.entity.ProductEntity;
import com.mallshop.modules.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Map<String, Object>> list() {
        return productRepository.findByStatus(1).stream()
                .map(this::toMap)
                .toList();
    }

    public Map<String, Object> getById(Long id) {
        return productRepository.findById(id).map(this::toMap).orElse(null);
    }

    private Map<String, Object> toMap(ProductEntity product) {
        return Map.of(
                "id", product.getId(),
                "name", product.getName(),
                "price", product.getPrice(),
                "stock", product.getStock()
        );
    }
}
