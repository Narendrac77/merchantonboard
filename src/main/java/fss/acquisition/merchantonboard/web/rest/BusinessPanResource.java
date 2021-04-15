/*
package fss.acquisition.merchantonboard.web.rest;

import com.fss.onboard.domain.BusinessPan;
import com.fss.onboard.repository.BusinessPanRepository;
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
 * REST controller for managing {@link com.fss.onboard.domain.BusinessPan}.
 *//*

@RestController
@RequestMapping("/api")
@Transactional
public class BusinessPanResource {

    private final Logger log = LoggerFactory.getLogger(BusinessPanResource.class);

    private static final String ENTITY_NAME = "businessPan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BusinessPanRepository businessPanRepository;

    public BusinessPanResource(BusinessPanRepository businessPanRepository) {
        this.businessPanRepository = businessPanRepository;
    }

    */
/**
     * {@code POST  /business-pans} : Create a new businessPan.
     *
     * @param businessPan the businessPan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new businessPan, or with status {@code 400 (Bad Request)} if the businessPan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PostMapping("/business-pans")
    public ResponseEntity<BusinessPan> createBusinessPan(@Valid @RequestBody BusinessPan businessPan) throws URISyntaxException {
        log.debug("REST request to save BusinessPan : {}", businessPan);
        if (businessPan.getId() != null) {
            throw new BadRequestAlertException("A new businessPan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessPan result = businessPanRepository.save(businessPan);
        return ResponseEntity
            .created(new URI("/api/business-pans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    */
/**
     * {@code PUT  /business-pans/:id} : Updates an existing businessPan.
     *
     * @param id the id of the businessPan to save.
     * @param businessPan the businessPan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated businessPan,
     * or with status {@code 400 (Bad Request)} if the businessPan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the businessPan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PutMapping("/business-pans/{id}")
    public ResponseEntity<BusinessPan> updateBusinessPan(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BusinessPan businessPan
    ) throws URISyntaxException {
        log.debug("REST request to update BusinessPan : {}, {}", id, businessPan);
        if (businessPan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, businessPan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!businessPanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BusinessPan result = businessPanRepository.save(businessPan);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, businessPan.getId().toString()))
            .body(result);
    }

    */
/**
     * {@code PATCH  /business-pans/:id} : Partial updates given fields of an existing businessPan, field will ignore if it is null
     *
     * @param id the id of the businessPan to save.
     * @param businessPan the businessPan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated businessPan,
     * or with status {@code 400 (Bad Request)} if the businessPan is not valid,
     * or with status {@code 404 (Not Found)} if the businessPan is not found,
     * or with status {@code 500 (Internal Server Error)} if the businessPan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PatchMapping(value = "/business-pans/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BusinessPan> partialUpdateBusinessPan(
        @PathVariable(value = "id", required = false) final Long id,
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
    }

    */
/**
     * {@code GET  /business-pans} : get all the businessPans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of businessPans in body.
     *//*

    @GetMapping("/business-pans")
    public List<BusinessPan> getAllBusinessPans() {
        log.debug("REST request to get all BusinessPans");
        return businessPanRepository.findAll();
    }

    */
/**
     * {@code GET  /business-pans/:id} : get the "id" businessPan.
     *
     * @param id the id of the businessPan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the businessPan, or with status {@code 404 (Not Found)}.
     *//*

    @GetMapping("/business-pans/{id}")
    public ResponseEntity<BusinessPan> getBusinessPan(@PathVariable Long id) {
        log.debug("REST request to get BusinessPan : {}", id);
        Optional<BusinessPan> businessPan = businessPanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(businessPan);
    }

    */
/**
     * {@code DELETE  /business-pans/:id} : delete the "id" businessPan.
     *
     * @param id the id of the businessPan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     *//*

    @DeleteMapping("/business-pans/{id}")
    public ResponseEntity<Void> deleteBusinessPan(@PathVariable Long id) {
        log.debug("REST request to delete BusinessPan : {}", id);
        businessPanRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
*/
