package com.example.Precept.Backend.APIs.modeller.Partner;

import com.example.Precept.Backend.APIs.exception.otherException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/partner")
public class PartnerController {
    private final PartnerService partnerService;
    private final PartnerRepository partnerRepository;

    @Autowired
    public PartnerController(PartnerService partnerService, PartnerRepository partnerRepository) {
        this.partnerService = partnerService;
        this.partnerRepository = partnerRepository;
    }

    @GetMapping("")
    public List<Partner> getPartner() {
        return partnerService.getPartner();
    }

    @PostMapping("create")
    public void registerNewPartner(@RequestBody Partner partner) {
        partnerService.addNewPartner(partner);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partner> getPartnerById(@PathVariable(value = "id") Long partnerID)
            throws ResourceNotFoundException {
        Partner partner = partnerRepository.findById(partnerID)
                .orElseThrow(() -> new ResourceNotFoundException("Partner not found for this id :: " + partnerID));
        return ResponseEntity.ok().body(partner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partner> updatePartner(@PathVariable(value = "id") Long partnerID,
                                                 @Valid @RequestBody Partner partnerDetails) throws ResourceNotFoundException {
        Partner partner = partnerRepository.findById(partnerID)
                .orElseThrow(() -> new ResourceNotFoundException("Partner not found for this id :: " + partnerID));

        partner.setPartnerName(partnerDetails.getPartnerName());
        final Partner updatedPartner = partnerRepository.save(partner);
        return ResponseEntity.ok(updatedPartner);
    }

    @DeleteMapping(path = "delete/{Id}")
    public void deletePartner(@PathVariable("Id") Long partnerID) {
        partnerService.deleteProduct(partnerID);
    }

}
