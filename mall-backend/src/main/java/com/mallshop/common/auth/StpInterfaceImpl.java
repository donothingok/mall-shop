package com.mallshop.common.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.mallshop.modules.auth.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

    private final AccountService accountService;

    public StpInterfaceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return accountService.findRoleList(String.valueOf(loginId));
    }
}
