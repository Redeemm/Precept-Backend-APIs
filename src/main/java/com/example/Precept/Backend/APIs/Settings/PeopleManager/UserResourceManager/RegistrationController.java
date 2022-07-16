package com.example.Precept.Backend.APIs.Settings.PeopleManager.UserResourceManager;

import com.example.Precept.Backend.APIs.exception.ApiRequestException;
import com.example.Precept.Backend.APIs.modeller.People.user.User;
import com.example.Precept.Backend.APIs.modeller.People.user.UserRepository;
import com.example.Precept.Backend.APIs.modeller.People.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user/auth")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @PostMapping("reg")
    public String register(@RequestBody RegistrationRequest registrationRequest) {
        return registrationService.register(registrationRequest);
    }

//    @PostMapping("login")
//    public Optional<LoginRequest> loginUser(@RequestBody User user) throws Exception {
//        String tempEmail = user.getEmail();
//        String tempPass = user.getPassword();
//        Optional<LoginRequest> Obj = null;
//        if (tempEmail != null && tempPass != null) {
//            Obj = registrationService.loginUser(tempEmail, tempPass);
//        }
//        if (Obj == null) {
//            throw new Exception("Bad credentials");
//        }
//        return Obj;
//    }

    @PostMapping("login")
    public String loginUser(@RequestBody User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        boolean userExists = userRepository
                .findByEmailAndPassword(user.getEmail(), user.getPassword())
                .isPresent();

        if (!userExists) {
            throw new ApiRequestException("Email or Password does not exist");
        }
        if (user.getEnabled() && user.isAccountNonExpired() && user.getLocked()) {
            throw new IllegalStateException("Bad Credentials");
        }
        return "User Logged In";
    }


    @GetMapping("reg/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
