package com.mallshop.modules.auth.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {

    private final Map<String, UserAccount> users = Map.of(
            "user", new UserAccount("10001", "user", "123456", List.of("user")),
            "admin", new UserAccount("90001", "admin", "123456", List.of("admin", "user"))
    );

    public Optional<UserAccount> validate(String username, String password) {
        UserAccount account = users.get(username);
        if (account == null || !account.password().equals(password)) {
            return Optional.empty();
        }
        return Optional.of(account);
    }

    public List<String> findRoleList(String loginId) {
        return users.values().stream()
                .filter(user -> user.userId().equals(loginId))
                .findFirst()
                .map(UserAccount::roles)
                .orElse(List.of());
    }

    public record UserAccount(String userId, String username, String password, List<String> roles) {
    }
}
