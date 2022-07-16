package com.example.Precept.Backend.APIs.Settings.PeopleManager.UserResourceManager;

import com.example.Precept.Backend.APIs.exception.ApiNotFoundException;
import com.example.Precept.Backend.APIs.exception.ApiRequestException;
import com.example.Precept.Backend.APIs.modeller.People.user.User;
import com.example.Precept.Backend.APIs.modeller.People.user.UserRepository;
import com.example.Precept.Backend.APIs.modeller.People.user.UserRole;
import com.example.Precept.Backend.APIs.modeller.People.user.UserService;
import com.example.Precept.Backend.APIs.Settings.registration.token.UserToken.ConfirmationToken;
import com.example.Precept.Backend.APIs.Settings.registration.token.UserToken.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new ApiRequestException("Email not valid");
        }
        String token = userService.signUpUser(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER
                )
        );



        return token;
    }



    public Optional<LoginRequest> loginUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }



    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new ApiNotFoundException("Token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new ApiRequestException("Email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new ApiRequestException("Token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "User successfully confirmed";
    }

    private String buildEmail(String name, String link) {
        return "Email template with link to activation";
    }

}
