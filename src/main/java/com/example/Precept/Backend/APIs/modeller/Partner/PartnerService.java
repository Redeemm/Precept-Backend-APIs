package com.example.Precept.Backend.APIs.modeller.Partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnerService {

    private final PartnerRepository partnerRepository;

    @Autowired
    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public List<Partner> getPartner(){
        return partnerRepository.findAll();
    }


    public void addNewPartner(Partner partner) {

        Optional<Partner> optionalStudent = partnerRepository.findPartnerByPartnerName(partner.getPartnerName());
        if (optionalStudent.isPresent()){
            throw new IllegalStateException("partner name exist!");
        }
        partnerRepository.save(partner);
    }

    public void deleteProduct(Long ProductID) {
        boolean exist = partnerRepository.existsById(ProductID);
        if (!exist) {
            throw new IllegalStateException("Student ID " + ProductID + " does not exist");
        }
        partnerRepository.deleteById(ProductID);
    }
}
