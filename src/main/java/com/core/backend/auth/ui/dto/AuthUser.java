package com.core.backend.auth.ui.dto;

import lombok.Getter;

@Getter
public class AuthUser {

    private final Long userId;

    public AuthUser(Long userId) {
        this.userId = userId;
    }
}
