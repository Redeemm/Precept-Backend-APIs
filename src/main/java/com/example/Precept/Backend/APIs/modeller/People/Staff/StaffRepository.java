package com.example.Precept.Backend.APIs.modeller.People.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Staff a " + "SET a.enabled = TRUE WHERE a.email = ?1")
    void enableUser(String email);

    Optional<Staff> findByEmailAndPassword(String email, String password);
}
