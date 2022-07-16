package com.example.Precept.Backend.APIs.Settings.PeopleManager.UserResourceManager;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest {
    private final String email;
    private final String password;


}
