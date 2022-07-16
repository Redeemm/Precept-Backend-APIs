package com.example.Precept.Backend.APIs.Settings.registration.token.StaffToken;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StaffConfirmationTokenService {

    private final StaffConfirmationTokenRepository staffConfirmationTokenRepository;

    public void saveConfirmationToken(StaffConfirmationToken token) {
        staffConfirmationTokenRepository.save(token);
    }
    public Optional<StaffConfirmationToken> getToken(String token) {
        return staffConfirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return staffConfirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
