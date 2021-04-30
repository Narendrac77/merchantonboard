package fss.acquisition.merchantonboard.web.rest;

import fss.acquisition.merchantonboard.domain.BusinessPan;
import fss.acquisition.merchantonboard.repository.BusinessPanRepository;
import fss.acquisition.merchantonboard.service.BusinessPanService;
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
public class BusinessPanResource {

    private final Logger log = LoggerFactory.getLogger(BusinessPanResource.class);

    private static final String ENTITY_NAME = "businessPan";

    @Autowired
    BusinessPanService businessPanService;

    private final BusinessPanRepository businessPanRepository;

    public BusinessPanResource(BusinessPanRepository businessPanRepository) {
        this.businessPanRepository = businessPanRepository;
    }

    @PostMapping("/business-pans")
    public String createBusinessPan(@Valid @RequestBody BusinessPan businessPan) throws Exception {
        log.debug("REST request to save BusinessPan : {}", businessPan);
        String result = businessPanService.createBusinessPan(businessPan);
            return result ;
    }

    @PutMapping("/business-pans/{mid}")
    public String updateBusinessPan(
            @PathVariable(value = "mid", required = true) final String mid,
            @Valid @RequestBody BusinessPan businessPan
    ) throws Exception {
        String result = businessPanService.updateBusinessPan(businessPan);
        return result;
    }

/*
    @PatchMapping(value = "/business-pans/{mid}", consumes = "application/merge-patch+json")
    public ResponseEntity<BusinessPan> partialUpdateBusinessPan(
        @PathVariable(value = "id", required = true) final String mid,
        @NotNull @RequestBody BusinessPan businessPan
    ) throws URISyntaxException {
        log.debug("REST request to partial update BusinessPan partially : {}, {}", id, businessPan);
        if (businessPan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, businessPan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!businessPanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BusinessPan> result = businessPanRepository
            .findById(businessPan.getId())
            .map(
                existingBusinessPan -> {
                    if (businessPan.getPanno() != null) {
                        existingBusinessPan.setPanno(businessPan.getPanno());
                    }
                    if (businessPan.getPandoc() != null) {
                        existingBusinessPan.setPandoc(businessPan.getPandoc());
                    }
                    if (businessPan.getPandocContentType() != null) {
                        existingBusinessPan.setPandocContentType(businessPan.getPandocContentType());
                    }
                    if (businessPan.getStatus() != null) {
                        existingBusinessPan.setStatus(businessPan.getStatus());
                    }

                    return existingBusinessPan;
                }
            )
            .map(businessPanRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, businessPan.getId().toString())
        );
    }*/


    @GetMapping("/business-pans")
    public List<BusinessPan> getAllBusinessPans() {
        log.debug("REST request to get all BusinessPans");
        return businessPanRepository.findAll();
    }

    @GetMapping("/business-pans/{mid}")
    public BusinessPan getBusinessPan(@PathVariable String mid) throws ResourseNotFoundException {
        log.debug("REST request to get BusinessPan : {}", mid);
        return businessPanService.getBusinessPan(mid);
    }


    @DeleteMapping("/business-pans/{mid}")
    public String deleteBusinessPan(@PathVariable String mid) {
        log.debug("REST request to delete BusinessPan : {}", mid);
        businessPanRepository.deleteByMid(mid);
        return "Deleted Successfully";
    }
}
