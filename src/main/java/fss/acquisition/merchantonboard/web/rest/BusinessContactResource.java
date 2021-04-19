package fss.acquisition.merchantonboard.web.rest;

import fss.acquisition.merchantonboard.domain.BusinessContact;
import fss.acquisition.merchantonboard.repository.BusinessContactRepository;
import fss.acquisition.merchantonboard.service.BusinessContactService;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api")
@Transactional
public class BusinessContactResource {

    private final Logger log = LoggerFactory.getLogger(BusinessContactResource.class);

    private static final String ENTITY_NAME = "businessContact";

    @Autowired
    BusinessContactService businessContactService;

    private final BusinessContactRepository businessContactRepository;

    public BusinessContactResource(BusinessContactRepository businessContactRepository) {
        this.businessContactRepository = businessContactRepository;
    }



    @PostMapping("/business-contacts")
    public String createBusinessContact(@Valid @RequestBody BusinessContact businessContact) throws ResourseNotFoundException {
        log.debug("REST request to save BusinessContact : {}", businessContact);
        businessContactService.createBusinessContact(businessContact);
        return "Created Successfully";
    }

    @PutMapping("/business-contacts/{mid}")
    public String updateBusinessContact(
        @PathVariable(value = "mid", required = true) final UUID mid,
        @Valid @RequestBody BusinessContact businessContact
    ) throws ResourseNotFoundException {
        log.debug("REST request to update BusinessContact : {}", businessContact);
         businessContactService.updateBusinessContact(businessContact);
        return "Updated successfully";
    }

/*    @PatchMapping(value = "/business-contacts/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BusinessContact> partialUpdateBusinessContact(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BusinessContact businessContact
    ) throws URISyntaxException {
        log.debug("REST request to partial update BusinessContact partially : {}, {}", id, businessContact);
        if (businessContact.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, businessContact.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!businessContactRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BusinessContact> result = businessContactRepository
            .findById(businessContact.getId())
            .map(
                existingBusinessContact -> {
                    if (businessContact.getContactname() != null) {
                        existingBusinessContact.setContactname(businessContact.getContactname());
                    }
                    if (businessContact.getContactmobileno() != null) {
                        existingBusinessContact.setContactmobileno(businessContact.getContactmobileno());
                    }
                    if (businessContact.getContactemailid() != null) {
                        existingBusinessContact.setContactemailid(businessContact.getContactemailid());
                    }
                    if (businessContact.getMid() != null) {
                        existingBusinessContact.setMid(businessContact.getMid());
                    }
                    if (businessContact.getContacttype() != null) {
                        existingBusinessContact.setContacttype(businessContact.getContacttype());
                    }

                    return existingBusinessContact;
                }
            )
            .map(businessContactRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, businessContact.getId().toString())
        );
    }*/
    @GetMapping("/business-contacts")
    public List<BusinessContact> getAllBusinessContacts() {
        log.debug("REST request to get all BusinessContacts");
        return businessContactRepository.findAll();
    }

    @GetMapping("/business-contacts/{mid}")
    public BusinessContact getBusinessContact(@PathVariable UUID mid) throws ResourseNotFoundException {
        log.debug("REST request to get BusinessContact : {}", mid);
        return businessContactService.getBusinessContact(mid);
    }


    @DeleteMapping("/business-contacts/{mid}")
    public String deleteBusinessContact(@PathVariable UUID mid) {
        log.debug("REST request to delete BusinessContact : {}", mid);
        businessContactRepository.deleteByMid(mid);
        return "Deleted Resource successfully";
    }
}
