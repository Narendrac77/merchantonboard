package fss.acquisition.merchantonboard.web.rest;


import fss.acquisition.merchantonboard.domain.BusinessOwner;
import fss.acquisition.merchantonboard.repository.BusinessOwnerRepository;
import fss.acquisition.merchantonboard.service.BusinessOwnerService;
import fss.acquisition.merchantonboard.web.rest.errors.GlobalExceptionHandler;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.namespace.QName;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@RestController
@RequestMapping("/api")
@Transactional
public class BusinessOwnerResource {

    private final Logger log = LoggerFactory.getLogger(BusinessOwnerResource.class);

    private static final String ENTITY_NAME = "businessOwner";

    @Autowired
    BusinessOwnerService businessOwnerService;

    private final BusinessOwnerRepository businessOwnerRepository;

    public BusinessOwnerResource(BusinessOwnerRepository businessOwnerRepository) {
        this.businessOwnerRepository = businessOwnerRepository;
    }



    @PostMapping("/business-owners")
    public String createBusinessOwner(@Valid @RequestBody BusinessOwner businessOwner) throws ResourseNotFoundException {
        log.debug("REST request to save BusinessOwner : {}", businessOwner);
        businessOwnerService.createBusinessOwner(businessOwner);
        return "Business Details has been created Successfully";
    }


    @PutMapping("/business-owners/{mobileno}")
    public String updateBusinessOwner(
        @PathVariable(value = "mobileno", required = true) final String mobileno,
        @Valid @RequestBody BusinessOwner businessOwner) throws ResourseNotFoundException {
        log.debug("REST request to update BusinessOwner : {}, {}", mobileno, businessOwner);
        businessOwnerService.updateBusinessOwner(businessOwner);
        return "Updated Successfully";
    }

    /*@PatchMapping(value = "/business-owners/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BusinessOwner> partialUpdateBusinessOwner(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BusinessOwner businessOwner
    ) throws URISyntaxException {
        log.debug("REST request to partial update BusinessOwner partially : {}, {}", id, businessOwner);
        if (businessOwner.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, businessOwner.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!businessOwnerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BusinessOwner> result = businessOwnerRepository
            .findById(businessOwner.getId())
            .map(
                existingBusinessOwner -> {
                    if (businessOwner.getName() != null) {
                        existingBusinessOwner.setName(businessOwner.getName());
                    }
                    if (businessOwner.getMobileno() != null) {
                        existingBusinessOwner.setMobileno(businessOwner.getMobileno());
                    }
                    if (businessOwner.getEmail() != null) {
                        existingBusinessOwner.setEmail(businessOwner.getEmail());
                    }
                    if (businessOwner.getAddress() != null) {
                        existingBusinessOwner.setAddress(businessOwner.getAddress());
                    }

                    return existingBusinessOwner;
                }
            )
            .map(businessOwnerRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, businessOwner.getId().toString())
        );
    }*/



    @GetMapping("/business-owners")
    public List<BusinessOwner> getAllBusinessOwners() {
        log.debug("REST request to get all BusinessOwners");
        return businessOwnerRepository.findAll();
    }



    @GetMapping("/business-owners/{mobileno}")
    public BusinessOwner getBusinessOwner(@PathVariable String mobileno) throws ResourseNotFoundException {
        log.debug("REST request to get BusinessOwner : {}", mobileno);
        return businessOwnerService.getBusinessOwner(mobileno);
    }

    @DeleteMapping("/business-owners/{mobileno}")
    public String deleteBusinessOwner(@PathVariable String mobileno) {
        log.debug("REST request to delete BusinessOwner : {}", mobileno);
        businessOwnerRepository.deleteByMobileno(mobileno);
        return "Deleted Successfully";
    }

}
