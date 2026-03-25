package com.mallshop.modules.admin;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.mallshop.common.model.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @SaCheckRole("admin")
    @GetMapping("/dashboard")
    public ApiResponse<Map<String, Object>> dashboard() {
        return ApiResponse.ok(Map.of(
                "gmv", 123456.78,
                "orderCount", 321,
                "newUsers", 56
        ));
    }
}
