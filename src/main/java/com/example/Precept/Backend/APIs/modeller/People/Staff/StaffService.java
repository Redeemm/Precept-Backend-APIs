package com.example.Precept.Backend.APIs.modeller.People.Staff;

import com.example.Precept.Backend.APIs.exception.ApiRequestException;
import com.example.Precept.Backend.APIs.Settings.registration.token.StaffToken.StaffConfirmationToken;
import com.example.Precept.Backend.APIs.Settings.registration.token.StaffToken.StaffConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StaffService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "User with email %s not found";
    private final StaffRepository staffRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StaffConfirmationTokenService staffConfirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return staffRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(Staff staff) {
        boolean userExists = staffRepository
                .findByEmail(staff.getEmail())
                .isPresent();

        if (userExists) {
            throw new ApiRequestException("Email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(staff.getPassword());

        staff.setPassword(encodedPassword);

        staffRepository.save(staff);

        String token = UUID.randomUUID().toString();
        StaffConfirmationToken staffConfirmationToken = new StaffConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                staff
        );

        staffConfirmationTokenService.saveConfirmationToken(staffConfirmationToken);

        return token;
    }

    public void enableUser(String email) {
        staffRepository.enableUser(email);
    }

    public List<Staff> getUsers() {
        return staffRepository.findAll();
    }

    public void addNewStaff(Staff staff) {

        Optional<Staff> optionalStudent = staffRepository.findByEmail(staff.getEmail());
        if (optionalStudent.isPresent()){
            throw new IllegalStateException("partner name exist!");
        }
        staffRepository.save(staff);
    }


    public void deleteProduct(Long AdminID) {
        boolean exist = staffRepository.existsById(AdminID);
        if (!exist) {
            throw new IllegalStateException("Student ID " + AdminID + " does not exist");
        }
        staffRepository.deleteById(AdminID);
    }
}
