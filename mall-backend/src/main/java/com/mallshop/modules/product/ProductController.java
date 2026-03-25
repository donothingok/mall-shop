package com.mallshop.modules.product;

import com.mallshop.common.model.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/public/products")
public class ProductController {

    @GetMapping
    public ApiResponse<List<Map<String, Object>>> list() {
        return ApiResponse.ok(List.of(
            Map.of("id", 1, "name", "示例商品A", "price", 99.00),
            Map.of("id", 2, "name", "示例商品B", "price", 199.00)
        ));
    }
}
