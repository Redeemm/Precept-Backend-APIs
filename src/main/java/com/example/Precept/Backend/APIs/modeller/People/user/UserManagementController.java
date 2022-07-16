package com.example.Precept.Backend.APIs.modeller.People.user;

import com.example.Precept.Backend.APIs.exception.otherException.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserManagementController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
//    @PreAuthorize("hasAnyRole('ADMIN, STAFF')") //comment it
    public List<User> getUsers() {
        return userService.getUsers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userID,
                                                 @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("Partner not found for this id :: " + userID));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());

        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("delete/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable("id") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
