package com.mallshop.modules.order;

import cn.dev33.satoken.stp.StpUtil;
import com.mallshop.common.model.ApiResponse;
import com.mallshop.modules.cart.entity.CartItemEntity;
import com.mallshop.modules.cart.service.CartService;
import com.mallshop.modules.order.dto.CreateOrderRequest;
import com.mallshop.modules.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @PostMapping
    public ApiResponse<Map<String, Object>> create(@Valid @RequestBody CreateOrderRequest req) {
        String userId = String.valueOf(StpUtil.getLoginId());
        List<CartItemEntity> cartItems = cartService.listEntity(userId);
        if (cartItems.isEmpty()) {
            return ApiResponse.fail(400, "购物车为空，无法下单");
        }
        Map<String, Object> order = orderService.createOrder(userId, cartItems, req.getAddress());
        cartService.clear(userId);
        return ApiResponse.ok(order);
    }

    @GetMapping
    public ApiResponse<List<Map<String, Object>>> list() {
        return ApiResponse.ok(orderService.list(String.valueOf(StpUtil.getLoginId())));
    }
}
