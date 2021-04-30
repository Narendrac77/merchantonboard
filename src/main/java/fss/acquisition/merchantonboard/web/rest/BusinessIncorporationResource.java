package fss.acquisition.merchantonboard.web.rest;

import fss.acquisition.merchantonboard.domain.BusinessIncorporation;
import fss.acquisition.merchantonboard.repository.BusinessIncorporationRepository;
import fss.acquisition.merchantonboard.service.BusinessIncorporationService;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api")
@Transactional
public class BusinessIncorporationResource {

    private final Logger log = LoggerFactory.getLogger(BusinessIncorporationResource.class);

    private static final String ENTITY_NAME = "businessIncoperation";

    @Autowired
    BusinessIncorporationService businessIncorporationService;

    private final BusinessIncorporationRepository businessIncorporationRepository;

    public BusinessIncorporationResource(BusinessIncorporationRepository businessIncorporationRepository) {
        this.businessIncorporationRepository = businessIncorporationRepository;
    }



    @PostMapping("/business-incoperations")
    public String createBusinessIncoperation(@Valid @RequestBody BusinessIncorporation businessIncorporation)
            throws Exception {
        log.debug("REST request to save BusinessIncoperation : {}", businessIncorporation);
        businessIncorporationService.createBusinessIncorporation(businessIncorporation);
        return "Created Successfully";
    }


    @PutMapping("/business-incoperations/{mid}")
    public String updateBusinessIncoperation(
        @PathVariable(value = "id", required = true) final String mid,
        @Valid @RequestBody BusinessIncorporation businessIncorporation
    )throws ResourseNotFoundException{
        businessIncorporationService.updateBusinessIncorporation(businessIncorporation);
        return "Updated Successfully";
    }

    /*@PatchMapping(value = "/business-incoperations/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BusinessIncoperation> partialUpdateBusinessIncoperation(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BusinessIncoperation businessIncoperation
    ) throws URISyntaxException {
        log.debug("REST request to partial update BusinessIncoperation partially : {}, {}", id, businessIncoperation);
        if (businessIncoperation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, businessIncoperation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!businessIncoperationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BusinessIncoperation> result = businessIncoperationRepository
            .findById(businessIncoperation.getId())
            .map(
                existingBusinessIncoperation -> {
                    if (businessIncoperation.getIncorporationno() != null) {
                        existingBusinessIncoperation.setIncorporationno(businessIncoperation.getIncorporationno());
                    }
                    if (businessIncoperation.getBusinessregisteredaddress() != null) {
                        existingBusinessIncoperation.setBusinessregisteredaddress(businessIncoperation.getBusinessregisteredaddress());
                    }
                    if (businessIncoperation.getBusinesslegalname() != null) {
                        existingBusinessIncoperation.setBusinesslegalname(businessIncoperation.getBusinesslegalname());
                    }
                    if (businessIncoperation.getIncorporationdoc() != null) {
                        existingBusinessIncoperation.setIncorporationdoc(businessIncoperation.getIncorporationdoc());
                    }
                    if (businessIncoperation.getIncorporationdocContentType() != null) {
                        existingBusinessIncoperation.setIncorporationdocContentType(businessIncoperation.getIncorporationdocContentType());
                    }
                    if (businessIncoperation.getStatus() != null) {
                        existingBusinessIncoperation.setStatus(businessIncoperation.getStatus());
                    }

                    return existingBusinessIncoperation;
                }
            )
            .map(businessIncoperationRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, businessIncoperation.getId().toString())
        );
    }*/


    @GetMapping("/business-incoperations")
    public List<BusinessIncorporation> getAllBusinessIncoperations() {
        log.debug("REST request to get all BusinessIncoperations");
        return businessIncorporationRepository.findAll();
    }

    @GetMapping("/business-incoperations/{mid}")
    public BusinessIncorporation getBusinessIncoperation(@PathVariable String mid) throws ResourseNotFoundException {
        log.debug("REST request to get BusinessIncoperation : {}", mid);
        return businessIncorporationService.getBusinessIncorporation(mid);
    }

    @DeleteMapping("/business-incoperations/{mid}")
    public String deleteBusinessIncoperation(@PathVariable String mid) {
        log.debug("REST request to delete BusinessIncoperation : {}", mid);
        businessIncorporationRepository.deleteByMid(mid);
        return "Deleted Successfully";
    }
}
