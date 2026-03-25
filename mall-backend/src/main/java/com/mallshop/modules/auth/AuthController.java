package com.mallshop.modules.auth;

import com.mallshop.common.model.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/public/auth")
public class AuthController {

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody Map<String, Object> payload) {
        return ApiResponse.ok(Map.of(
            "token", "demo-token",
            "user", Map.of("id", 1, "nickname", payload.getOrDefault("username", "guest"))
        ));
    }
}
