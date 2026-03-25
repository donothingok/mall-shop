package com.mallshop.modules.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_no", nullable = false, unique = true, length = 64)
    private String orderNo;

    @Column(name = "user_id", nullable = false, length = 32)
    private String userId;

    @Column(nullable = false, length = 32)
    private String status;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
