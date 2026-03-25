package com.mallshop.modules.auth.service;

import com.mallshop.modules.auth.entity.UserAccountEntity;
import com.mallshop.modules.auth.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final UserAccountRepository userAccountRepository;

    public AccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public Optional<UserAccount> validate(String username, String password) {
        return userAccountRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .map(this::toAccount);
    }

    public List<String> findRoleList(String loginId) {
        return userAccountRepository.findByUserId(loginId)
                .map(user -> Arrays.stream(user.getRoleCodes().split(","))
                        .map(String::trim)
                        .filter(role -> !role.isEmpty())
                        .toList())
                .orElse(List.of());
    }

    private UserAccount toAccount(UserAccountEntity entity) {
        return new UserAccount(
                entity.getUserId(),
                entity.getUsername(),
                entity.getPassword(),
                findRoleList(entity.getUserId())
        );
    }

    public record UserAccount(String userId, String username, String password, List<String> roles) {
    }
}
