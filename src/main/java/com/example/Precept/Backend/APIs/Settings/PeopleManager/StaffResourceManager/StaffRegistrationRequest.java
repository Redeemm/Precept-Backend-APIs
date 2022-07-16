package com.example.Precept.Backend.APIs.Settings.PeopleManager.StaffResourceManager;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class StaffRegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
