package com.example.Precept.Backend.APIs.modeller.People.Staff;

import com.example.Precept.Backend.APIs.Settings.security.Staff.StaffPermission;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum StaffRole {
    ADMIN(Sets.newHashSet(StaffPermission.STAFF_READ, StaffPermission.STAFF_WRITE)),
    STAFF(Sets.newHashSet(StaffPermission.STAFF_READ));

    private final Set<StaffPermission> permissions;
}
