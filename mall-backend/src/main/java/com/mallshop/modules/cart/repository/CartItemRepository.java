package com.mallshop.modules.cart.repository;

import com.mallshop.modules.cart.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    List<CartItemEntity> findByUserId(String userId);

    void deleteByUserId(String userId);
}
