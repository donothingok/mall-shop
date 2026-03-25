package com.mallshop.modules.cart;

import cn.dev33.satoken.stp.StpUtil;
import com.mallshop.common.model.ApiResponse;
import com.mallshop.modules.cart.dto.AddCartItemRequest;
import com.mallshop.modules.cart.service.CartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ApiResponse<List<Map<String, Object>>> list() {
        return ApiResponse.ok(cartService.list(String.valueOf(StpUtil.getLoginId())));
    }

    @PostMapping("/items")
    public ApiResponse<String> add(@Valid @RequestBody AddCartItemRequest req) {
        cartService.add(String.valueOf(StpUtil.getLoginId()), req);
        return ApiResponse.ok("added");
    }
}
