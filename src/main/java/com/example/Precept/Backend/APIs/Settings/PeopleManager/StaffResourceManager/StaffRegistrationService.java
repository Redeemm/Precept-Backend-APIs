package com.example.Precept.Backend.APIs.Settings.PeopleManager.StaffResourceManager;

import com.example.Precept.Backend.APIs.Settings.registration.token.StaffToken.StaffConfirmationToken;
import com.example.Precept.Backend.APIs.Settings.registration.token.StaffToken.StaffConfirmationTokenService;
import com.example.Precept.Backend.APIs.exception.ApiNotFoundException;
import com.example.Precept.Backend.APIs.exception.ApiRequestException;
import com.example.Precept.Backend.APIs.modeller.People.Staff.Staff;
import com.example.Precept.Backend.APIs.modeller.People.Staff.StaffRole;
import com.example.Precept.Backend.APIs.modeller.People.Staff.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class StaffRegistrationService {

    private final StaffService staffService;
    private final SttaffEmailValidator sttaffEmailValidator;
    private final StaffConfirmationTokenService staffConfirmationTokenService;

    public String register(StaffRegistrationRequest request) {
        boolean isValidEmail = sttaffEmailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new ApiRequestException("Email not valid");
        }
        String token = staffService.signUpUser(
                new Staff(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        StaffRole.STAFF
                )
        );


        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        StaffConfirmationToken staffConfirmationToken = staffConfirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new ApiNotFoundException("Token not found"));

        if (staffConfirmationToken.getConfirmedAt() != null) {
            throw new ApiRequestException("Email already confirmed");
        }

        LocalDateTime expiredAt = staffConfirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new ApiRequestException("Token expired");
        }

        staffConfirmationTokenService.setConfirmedAt(token);
        staffService.enableUser(
                staffConfirmationToken.getStaff().getEmail());
        return "User successfully confirmed";
    }

    private String buildEmail(String name, String link) {
        return "Email template with link to activation";
    }
}
