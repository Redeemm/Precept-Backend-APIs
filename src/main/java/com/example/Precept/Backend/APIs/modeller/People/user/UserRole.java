package com.example.Precept.Backend.APIs.modeller.People.user;

import com.example.Precept.Backend.APIs.Settings.security.User.UserPermission;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum UserRole {
    USER(Sets.newHashSet());

    private final Set<UserPermission> permissions;
}
