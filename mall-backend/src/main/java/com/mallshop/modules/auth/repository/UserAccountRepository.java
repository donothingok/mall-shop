package com.mallshop.modules.auth.repository;

import com.mallshop.modules.auth.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {
    Optional<UserAccountEntity> findByUsername(String username);

    Optional<UserAccountEntity> findByUserId(String userId);
}
