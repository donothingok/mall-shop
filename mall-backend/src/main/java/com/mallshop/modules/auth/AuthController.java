package com.mallshop.modules.auth;

import cn.dev33.satoken.stp.StpUtil;
import com.mallshop.common.model.ApiResponse;
import com.mallshop.modules.auth.dto.LoginRequest;
import com.mallshop.modules.auth.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/public/auth")
public class AuthController {

    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@Valid @RequestBody LoginRequest payload) {
        return accountService.validate(payload.getUsername(), payload.getPassword())
                .map(account -> {
                    StpUtil.login(account.userId());
                    return ApiResponse.ok(Map.of(
                            "token", StpUtil.getTokenValue(),
                            "userId", account.userId(),
                            "username", account.username(),
                            "roles", account.roles()
                    ));
                })
                .orElse(ApiResponse.fail(401, "用户名或密码错误"));
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout() {
        StpUtil.logout();
        return ApiResponse.ok("logout success");
    }

    @GetMapping("/ping")
    public ApiResponse<String> ping() {
        return ApiResponse.ok("auth public endpoint ok");
    }
}
