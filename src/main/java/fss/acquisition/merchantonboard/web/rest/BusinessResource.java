/*
package fss.acquisition.merchantonboard.web.rest;

import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.repository.BusinessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Transactional
public class BusinessResource {

    private final Logger log = LoggerFactory.getLogger(BusinessResource.class);

    private static final String ENTITY_NAME = "business";


    private BusinessRepository businessRepository;

    public BusinessResource(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }



    @PostMapping("/businesses")
    public ResponseEntity<Business> createBusiness(@RequestBody Business business) throws URISyntaxException {
        log.debug("REST request to save Business : {}", business);
        if (business.getId() != null) {
            throw new BadRequestAlertException("A new business cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Business result = businessRepository.save(business);
        return ResponseEntity
            .created(new URI("/api/businesses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

*
     * {@code PUT  /businesses/:id} : Updates an existing business.
     *
     * @param id the id of the business to save.
     * @param business the business to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated business,
     * or with status {@code 400 (Bad Request)} if the business is not valid,
     * or with status {@code 500 (Internal Server Error)} if the business couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.


    @PutMapping("/businesses/{id}")
    public ResponseEntity<Business> updateBusiness(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Business business
    ) throws URISyntaxException {
        log.debug("REST request to update Business : {}, {}", id, business);
        if (business.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, business.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!businessRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Business result = businessRepository.save(business);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, business.getId().toString()))
            .body(result);
    }

*
     * {@code PATCH  /businesses/:id} : Partial updates given fields of an existing business, field will ignore if it is null
     *
     * @param id the id of the business to save.
     * @param business the business to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated business,
     * or with status {@code 400 (Bad Request)} if the business is not valid,
     * or with status {@code 404 (Not Found)} if the business is not found,
     * or with status {@code 500 (Internal Server Error)} if the business couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.


    @PatchMapping(value = "/businesses/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Business> partialUpdateBusiness(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Business business
    ) throws URISyntaxException {
        log.debug("REST request to partial update Business partially : {}, {}", id, business);
        if (business.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, business.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!businessRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Business> result = businessRepository
            .findById(business.getId())
            .map(
                existingBusiness -> {
                    if (business.getDisplayname() != null) {
                        existingBusiness.setDisplayname(business.getDisplayname());
                    }
                    if (business.getBusinesstype() != null) {
                        existingBusiness.setBusinesstype(business.getBusinesstype());
                    }
                    if (business.getBusinesscategory() != null) {
                        existingBusiness.setBusinesscategory(business.getBusinesscategory());
                    }
                    if (business.getBusinesssubcategory() != null) {
                        existingBusiness.setBusinesssubcategory(business.getBusinesssubcategory());
                    }
                    if (business.getCommunicationaddress() != null) {
                        existingBusiness.setCommunicationaddress(business.getCommunicationaddress());
                    }
                    if (business.getWesiteurl() != null) {
                        existingBusiness.setWesiteurl(business.getWesiteurl());
                    }
                    if (business.getAge() != null) {
                        existingBusiness.setAge(business.getAge());
                    }
                    if (business.getTurnover() != null) {
                        existingBusiness.setTurnover(business.getTurnover());
                    }
                    if (business.getMid() != null) {
                        existingBusiness.setMid(business.getMid());
                    }
                    if (business.getRiskscoring() != null) {
                        existingBusiness.setRiskscoring(business.getRiskscoring());
                    }
                    if (business.getStatus() != null) {
                        existingBusiness.setStatus(business.getStatus());
                    }

                    return existingBusiness;
                }
            )
            .map(businessRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, business.getId().toString())
        );
    }

*
     * {@code GET  /businesses} : get all the businesses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of businesses in body.


    @GetMapping("/businesses")
    public List<Business> getAllBusinesses() {
        log.debug("REST request to get all Businesses");
        return businessRepository.findAll();
    }

*
     * {@code GET  /businesses/:id} : get the "id" business.
     *
     * @param id the id of the business to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the business, or with status {@code 404 (Not Found)}.


    @GetMapping("/businesses/{id}")
    public ResponseEntity<Business> getBusiness(@PathVariable Long id) {
        log.debug("REST request to get Business : {}", id);
        Optional<Business> business = businessRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(business);
    }

*
     * {@code DELETE  /businesses/:id} : delete the "id" business.
     *
     * @param id the id of the business to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.


    @DeleteMapping("/businesses/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long id) {
        log.debug("REST request to delete Business : {}", id);
        businessRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
*/
