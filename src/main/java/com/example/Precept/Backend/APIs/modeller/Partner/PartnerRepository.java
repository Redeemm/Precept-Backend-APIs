package com.example.Precept.Backend.APIs.modeller.Partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    @Query("SELECT s FROM Partner s WHERE s.partnerName = ?1")
    Optional<Partner> findPartnerByPartnerName(String partnerName);

}
