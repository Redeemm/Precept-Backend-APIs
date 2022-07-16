package com.example.Precept.Backend.APIs.Settings.security.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPermission {
    USER_READ("user:read");

    private final String permission;
}
