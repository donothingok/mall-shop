package com.mallshop.modules.cart.service;

import com.mallshop.modules.cart.dto.AddCartItemRequest;
import com.mallshop.modules.cart.entity.CartItemEntity;
import com.mallshop.modules.cart.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<Map<String, Object>> list(String userId) {
        return cartItemRepository.findByUserId(userId).stream()
                .map(item -> Map.of(
                        "id", item.getId(),
                        "productId", item.getProductId(),
                        "quantity", item.getQuantity()
                ))
                .toList();
    }

    public void add(String userId, AddCartItemRequest req) {
        CartItemEntity entity = new CartItemEntity();
        entity.setUserId(userId);
        entity.setProductId(req.getProductId());
        entity.setQuantity(req.getQuantity());
        entity.setCreatedAt(LocalDateTime.now());
        cartItemRepository.save(entity);
    }

    public List<CartItemEntity> listEntity(String userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void clear(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
