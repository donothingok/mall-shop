package com.mallshop.modules.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sys_user")
@Getter
@Setter
public class UserAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true, length = 32)
    private String userId;

    @Column(nullable = false, unique = true, length = 32)
    private String username;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(name = "role_codes", nullable = false, length = 128)
    private String roleCodes;
}
