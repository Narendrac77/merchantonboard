/*
package fss.acquisition.merchantonboard.web.rest;

import com.fss.onboard.domain.BusinessContact;
import com.fss.onboard.repository.BusinessContactRepository;
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
 * REST controller for managing {@link com.fss.onboard.domain.BusinessContact}.
 *//*

@RestController
@RequestMapping("/api")
@Transactional
public class BusinessContactResource {

    private final Logger log = LoggerFactory.getLogger(BusinessContactResource.class);

    private static final String ENTITY_NAME = "businessContact";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BusinessContactRepository businessContactRepository;

    public BusinessContactResource(BusinessContactRepository businessContactRepository) {
        this.businessContactRepository = businessContactRepository;
    }

    */
/**
     * {@code POST  /business-contacts} : Create a new businessContact.
     *
     * @param businessContact the businessContact to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new businessContact, or with status {@code 400 (Bad Request)} if the businessContact has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PostMapping("/business-contacts")
    public ResponseEntity<BusinessContact> createBusinessContact(@Valid @RequestBody BusinessContact businessContact)
        throws URISyntaxException {
        log.debug("REST request to save BusinessContact : {}", businessContact);
        if (businessContact.getId() != null) {
            throw new BadRequestAlertException("A new businessContact cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessContact result = businessContactRepository.save(businessContact);
        return ResponseEntity
            .created(new URI("/api/business-contacts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    */
/**
     * {@code PUT  /business-contacts/:id} : Updates an existing businessContact.
     *
     * @param id the id of the businessContact to save.
     * @param businessContact the businessContact to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated businessContact,
     * or with status {@code 400 (Bad Request)} if the businessContact is not valid,
     * or with status {@code 500 (Internal Server Error)} if the businessContact couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PutMapping("/business-contacts/{id}")
    public ResponseEntity<BusinessContact> updateBusinessContact(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BusinessContact businessContact
    ) throws URISyntaxException {
        log.debug("REST request to update BusinessContact : {}, {}", id, businessContact);
        if (businessContact.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, businessContact.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!businessContactRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BusinessContact result = businessContactRepository.save(businessContact);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, businessContact.getId().toString()))
            .body(result);
    }

    */
/**
     * {@code PATCH  /business-contacts/:id} : Partial updates given fields of an existing businessContact, field will ignore if it is null
     *
     * @param id the id of the businessContact to save.
     * @param businessContact the businessContact to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated businessContact,
     * or with status {@code 400 (Bad Request)} if the businessContact is not valid,
     * or with status {@code 404 (Not Found)} if the businessContact is not found,
     * or with status {@code 500 (Internal Server Error)} if the businessContact couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PatchMapping(value = "/business-contacts/{id}", consumes = "application/merge-patch+json")
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
    }

    */
/**
     * {@code GET  /business-contacts} : get all the businessContacts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of businessContacts in body.
     *//*

    @GetMapping("/business-contacts")
    public List<BusinessContact> getAllBusinessContacts() {
        log.debug("REST request to get all BusinessContacts");
        return businessContactRepository.findAll();
    }

    */
/**
     * {@code GET  /business-contacts/:id} : get the "id" businessContact.
     *
     * @param id the id of the businessContact to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the businessContact, or with status {@code 404 (Not Found)}.
     *//*

    @GetMapping("/business-contacts/{id}")
    public ResponseEntity<BusinessContact> getBusinessContact(@PathVariable Long id) {
        log.debug("REST request to get BusinessContact : {}", id);
        Optional<BusinessContact> businessContact = businessContactRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(businessContact);
    }

    */
/**
     * {@code DELETE  /business-contacts/:id} : delete the "id" businessContact.
     *
     * @param id the id of the businessContact to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     *//*

    @DeleteMapping("/business-contacts/{id}")
    public ResponseEntity<Void> deleteBusinessContact(@PathVariable Long id) {
        log.debug("REST request to delete BusinessContact : {}", id);
        businessContactRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
*/
