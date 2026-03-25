package com.mallshop.modules.order.service;

import com.mallshop.modules.cart.entity.CartItemEntity;
import com.mallshop.modules.order.entity.OrderEntity;
import com.mallshop.modules.order.entity.OrderItemEntity;
import com.mallshop.modules.order.repository.OrderItemRepository;
import com.mallshop.modules.order.repository.OrderRepository;
import com.mallshop.modules.product.entity.ProductEntity;
import com.mallshop.modules.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Map<String, Object> createOrder(String userId, List<CartItemEntity> cartItems, String address) {
        String orderNo = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        OrderEntity order = new OrderEntity();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setStatus("CREATED");
        order.setAddress(address);
        order.setCreatedAt(LocalDateTime.now());
        orderRepository.save(order);

        List<Map<String, Object>> itemSnapshots = new ArrayList<>();

        for (CartItemEntity cartItem : cartItems) {
            ProductEntity product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("商品不存在: " + cartItem.getProductId()));

            OrderItemEntity item = new OrderItemEntity();
            item.setOrderId(order.getId());
            item.setProductId(product.getId());
            item.setProductName(product.getName());
            item.setPrice(product.getPrice());
            item.setQuantity(cartItem.getQuantity());
            orderItemRepository.save(item);

            itemSnapshots.add(Map.of(
                    "productId", product.getId(),
                    "productName", product.getName(),
                    "price", product.getPrice(),
                    "quantity", cartItem.getQuantity()
            ));
        }

        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("orderNo", orderNo);
        orderMap.put("status", order.getStatus());
        orderMap.put("address", address);
        orderMap.put("createdAt", order.getCreatedAt().toString());
        orderMap.put("items", itemSnapshots);
        return orderMap;
    }

    public List<Map<String, Object>> list(String userId) {
        return orderRepository.findByUserIdOrderByIdDesc(userId).stream().map(order -> {
            List<Map<String, Object>> items = orderItemRepository.findByOrderId(order.getId()).stream()
                    .map(item -> Map.of(
                            "productId", item.getProductId(),
                            "productName", item.getProductName(),
                            "price", item.getPrice(),
                            "quantity", item.getQuantity()
                    ))
                    .toList();

            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("orderNo", order.getOrderNo());
            orderMap.put("status", order.getStatus());
            orderMap.put("address", order.getAddress());
            orderMap.put("createdAt", order.getCreatedAt().toString());
            orderMap.put("items", items);
            return orderMap;
        }).toList();
    }
}
