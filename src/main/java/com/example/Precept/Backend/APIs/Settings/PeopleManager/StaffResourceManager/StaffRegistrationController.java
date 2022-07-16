package com.example.Precept.Backend.APIs.Settings.PeopleManager.StaffResourceManager;

import com.example.Precept.Backend.APIs.exception.ApiRequestException;
import com.example.Precept.Backend.APIs.modeller.People.Staff.Staff;
import com.example.Precept.Backend.APIs.modeller.People.Staff.StaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/staff/auth")
@AllArgsConstructor
public class StaffRegistrationController {

    private final StaffRegistrationService staffRegistrationService;
    private final StaffRepository staffRepository;

    @PostMapping("reg")
    public String register(@RequestBody StaffRegistrationRequest request) {
        return staffRegistrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return staffRegistrationService.confirmToken(token);
    }


    @PostMapping("login")
    public String loginUser(@RequestBody Staff staff) {
        boolean staffExists = staffRepository
                .findByEmailAndPassword(staff.getEmail(), staff.getPassword())
                .isPresent();

        if (!staffExists && staff.isEnabled()) {
            throw new ApiRequestException("Email or Password does not exist");
        }
        if (staff.getEnabled() && staff.isAccountNonExpired() && staff.getLocked()) {
            throw new IllegalStateException("Bad Credentials");
        }
        return "User Logged In";
    }
}
