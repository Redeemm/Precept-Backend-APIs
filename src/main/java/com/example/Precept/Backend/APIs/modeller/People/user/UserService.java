package com.example.Precept.Backend.APIs.modeller.People.user;

import com.example.Precept.Backend.APIs.exception.ApiRequestException;
import com.example.Precept.Backend.APIs.Settings.registration.token.UserToken.ConfirmationToken;
import com.example.Precept.Backend.APIs.Settings.registration.token.UserToken.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "User with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }


    public String signUpUser(User user) {
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            throw new ApiRequestException("Email already taken");
        }

//        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return "Your token is: " + token;
    }



    public String LoginUser(User user) {
        boolean userExists = userRepository
                .findByEmailAndPassword(user.getEmail(), user.getPassword())
                .isPresent();

        if (userExists && user.getEnabled()) {
            throw new ApiRequestException("Email/password already taken");
        }


        userRepository.equals(user);

        return "login successfully";
    }





    public void enableUser(String email) {
        userRepository.enableUser(email);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public void deleteUser(Long userID) {
        boolean exist = userRepository.existsById(userID);
        if (!exist) {
            throw new IllegalStateException("User ID " + userID + " does not exist");
        }
        userRepository.deleteById(userID);
    }
}
