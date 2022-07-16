package com.example.Precept.Backend.APIs.modeller.People.user;

import com.example.Precept.Backend.APIs.Settings.PeopleManager.UserResourceManager.LoginRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<LoginRequest> findByEmailAndPassword(String email, String password);

    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.enabled = TRUE WHERE a.email = ?1")
    void enableUser(String email);


//    public User findUserByEmailAndPassword (String email, String password);
}

