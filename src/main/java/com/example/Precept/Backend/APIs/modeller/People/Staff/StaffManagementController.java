package com.example.Precept.Backend.APIs.modeller.People.Staff;

import com.example.Precept.Backend.APIs.exception.otherException.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/staff")
@AllArgsConstructor
public class StaffManagementController {

    private final StaffService staffService;
    private final StaffRepository staffRepository;

    @GetMapping
//    @PreAuthorize("hasAnyRole('CUSTOMER, MODERATOR, USER')")
    public List<Staff> getUsers() {
        return staffService.getUsers();
    }


    @PostMapping("create")
    public void registerNewStaff(@RequestBody Staff staff) {
        staffService.addNewStaff(staff);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable(value = "id") Long staffID)
            throws ResourceNotFoundException {
        Staff staff = staffRepository.findById(staffID)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found for this id :: " + staffID));
        return ResponseEntity.ok().body(staff);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable(value = "id") Long staffID,
                                             @Valid @RequestBody Staff staffDetail) throws ResourceNotFoundException {
        Staff staff = staffRepository.findById(staffID)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found for this id :: " + staffID));

        staff.setEmail(staffDetail.getEmail());
        staff.setPassword(staffDetail.getPassword());
        staff.setFirstName(staffDetail.getFirstName());
        staff.setLastName(staffDetail.getLastName());
        final Staff updatedStaff = staffRepository.save(staff);
        return ResponseEntity.ok(updatedStaff);
    }


    @DeleteMapping(path = "delete/{Id}")
    public void deleteStaff(@PathVariable("Id") Long staffID) {
        staffService.deleteProduct(staffID);
    }
}
