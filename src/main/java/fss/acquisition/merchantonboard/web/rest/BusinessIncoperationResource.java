/*
package fss.acquisition.merchantonboard.web.rest;

import com.fss.onboard.domain.BusinessIncoperation;
import com.fss.onboard.repository.BusinessIncoperationRepository;
import com.fss.onboard.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

*/
/**
 * REST controller for managing {@link com.fss.onboard.domain.BusinessIncoperation}.
 *//*

@RestController
@RequestMapping("/api")
@Transactional
public class BusinessIncoperationResource {

    private final Logger log = LoggerFactory.getLogger(BusinessIncoperationResource.class);

    private static final String ENTITY_NAME = "businessIncoperation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BusinessIncoperationRepository businessIncoperationRepository;

    public BusinessIncoperationResource(BusinessIncoperationRepository businessIncoperationRepository) {
        this.businessIncoperationRepository = businessIncoperationRepository;
    }

    */
/**
     * {@code POST  /business-incoperations} : Create a new businessIncoperation.
     *
     * @param businessIncoperation the businessIncoperation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new businessIncoperation, or with status {@code 400 (Bad Request)} if the businessIncoperation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PostMapping("/business-incoperations")
    public ResponseEntity<BusinessIncoperation> createBusinessIncoperation(@Valid @RequestBody BusinessIncoperation businessIncoperation)
        throws URISyntaxException {
        log.debug("REST request to save BusinessIncoperation : {}", businessIncoperation);
        if (businessIncoperation.getId() != null) {
            throw new BadRequestAlertException("A new businessIncoperation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessIncoperation result = businessIncoperationRepository.save(businessIncoperation);
        return ResponseEntity
            .created(new URI("/api/business-incoperations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    */
/**
     * {@code PUT  /business-incoperations/:id} : Updates an existing businessIncoperation.
     *
     * @param id the id of the businessIncoperation to save.
     * @param businessIncoperation the businessIncoperation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated businessIncoperation,
     * or with status {@code 400 (Bad Request)} if the businessIncoperation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the businessIncoperation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PutMapping("/business-incoperations/{id}")
    public ResponseEntity<BusinessIncoperation> updateBusinessIncoperation(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BusinessIncoperation businessIncoperation
    ) throws URISyntaxException {
        log.debug("REST request to update BusinessIncoperation : {}, {}", id, businessIncoperation);
        if (businessIncoperation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, businessIncoperation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!businessIncoperationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BusinessIncoperation result = businessIncoperationRepository.save(businessIncoperation);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, businessIncoperation.getId().toString()))
            .body(result);
    }

    */
/**
     * {@code PATCH  /business-incoperations/:id} : Partial updates given fields of an existing businessIncoperation, field will ignore if it is null
     *
     * @param id the id of the businessIncoperation to save.
     * @param businessIncoperation the businessIncoperation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated businessIncoperation,
     * or with status {@code 400 (Bad Request)} if the businessIncoperation is not valid,
     * or with status {@code 404 (Not Found)} if the businessIncoperation is not found,
     * or with status {@code 500 (Internal Server Error)} if the businessIncoperation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PatchMapping(value = "/business-incoperations/{id}", consumes = "application/merge-patch+json")
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
    }

    */
/**
     * {@code GET  /business-incoperations} : get all the businessIncoperations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of businessIncoperations in body.
     *//*

    @GetMapping("/business-incoperations")
    public List<BusinessIncoperation> getAllBusinessIncoperations() {
        log.debug("REST request to get all BusinessIncoperations");
        return businessIncoperationRepository.findAll();
    }

    */
/**
     * {@code GET  /business-incoperations/:id} : get the "id" businessIncoperation.
     *
     * @param id the id of the businessIncoperation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the businessIncoperation, or with status {@code 404 (Not Found)}.
     *//*

    @GetMapping("/business-incoperations/{id}")
    public ResponseEntity<BusinessIncoperation> getBusinessIncoperation(@PathVariable Long id) {
        log.debug("REST request to get BusinessIncoperation : {}", id);
        Optional<BusinessIncoperation> businessIncoperation = businessIncoperationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(businessIncoperation);
    }

    */
/**
     * {@code DELETE  /business-incoperations/:id} : delete the "id" businessIncoperation.
     *
     * @param id the id of the businessIncoperation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     *//*

    @DeleteMapping("/business-incoperations/{id}")
    public ResponseEntity<Void> deleteBusinessIncoperation(@PathVariable Long id) {
        log.debug("REST request to delete BusinessIncoperation : {}", id);
        businessIncoperationRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
*/
