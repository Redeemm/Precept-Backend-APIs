package com.example.Precept.Backend.APIs.Settings.security.Staff;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StaffPermission {
    STAFF_READ("user:read"),
    STAFF_WRITE("user:write");

    private final String permission;
}
