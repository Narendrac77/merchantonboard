/*
package fss.acquisition.merchantonboard.web.rest;

import com.fss.onboard.domain.GstinDeatils;
import com.fss.onboard.repository.GstinDeatilsRepository;
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
 * REST controller for managing {@link com.fss.onboard.domain.GstinDeatils}.
 *//*

@RestController
@RequestMapping("/api")
@Transactional
public class GstinDeatilsResource {

    private final Logger log = LoggerFactory.getLogger(GstinDeatilsResource.class);

    private static final String ENTITY_NAME = "gstinDeatils";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GstinDeatilsRepository gstinDeatilsRepository;

    public GstinDeatilsResource(GstinDeatilsRepository gstinDeatilsRepository) {
        this.gstinDeatilsRepository = gstinDeatilsRepository;
    }

    */
/**
     * {@code POST  /gstin-deatils} : Create a new gstinDeatils.
     *
     * @param gstinDeatils the gstinDeatils to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gstinDeatils, or with status {@code 400 (Bad Request)} if the gstinDeatils has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PostMapping("/gstin-deatils")
    public ResponseEntity<GstinDeatils> createGstinDeatils(@Valid @RequestBody GstinDeatils gstinDeatils) throws URISyntaxException {
        log.debug("REST request to save GstinDeatils : {}", gstinDeatils);
        if (gstinDeatils.getId() != null) {
            throw new BadRequestAlertException("A new gstinDeatils cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GstinDeatils result = gstinDeatilsRepository.save(gstinDeatils);
        return ResponseEntity
            .created(new URI("/api/gstin-deatils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    */
/**
     * {@code PUT  /gstin-deatils/:id} : Updates an existing gstinDeatils.
     *
     * @param id the id of the gstinDeatils to save.
     * @param gstinDeatils the gstinDeatils to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gstinDeatils,
     * or with status {@code 400 (Bad Request)} if the gstinDeatils is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gstinDeatils couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PutMapping("/gstin-deatils/{id}")
    public ResponseEntity<GstinDeatils> updateGstinDeatils(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GstinDeatils gstinDeatils
    ) throws URISyntaxException {
        log.debug("REST request to update GstinDeatils : {}, {}", id, gstinDeatils);
        if (gstinDeatils.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, gstinDeatils.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!gstinDeatilsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GstinDeatils result = gstinDeatilsRepository.save(gstinDeatils);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gstinDeatils.getId().toString()))
            .body(result);
    }

    */
/**
     * {@code PATCH  /gstin-deatils/:id} : Partial updates given fields of an existing gstinDeatils, field will ignore if it is null
     *
     * @param id the id of the gstinDeatils to save.
     * @param gstinDeatils the gstinDeatils to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gstinDeatils,
     * or with status {@code 400 (Bad Request)} if the gstinDeatils is not valid,
     * or with status {@code 404 (Not Found)} if the gstinDeatils is not found,
     * or with status {@code 500 (Internal Server Error)} if the gstinDeatils couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PatchMapping(value = "/gstin-deatils/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<GstinDeatils> partialUpdateGstinDeatils(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GstinDeatils gstinDeatils
    ) throws URISyntaxException {
        log.debug("REST request to partial update GstinDeatils partially : {}, {}", id, gstinDeatils);
        if (gstinDeatils.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, gstinDeatils.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!gstinDeatilsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GstinDeatils> result = gstinDeatilsRepository
            .findById(gstinDeatils.getId())
            .map(
                existingGstinDeatils -> {
                    if (gstinDeatils.getGstinno() != null) {
                        existingGstinDeatils.setGstinno(gstinDeatils.getGstinno());
                    }
                    if (gstinDeatils.getGstindoc() != null) {
                        existingGstinDeatils.setGstindoc(gstinDeatils.getGstindoc());
                    }
                    if (gstinDeatils.getGstindocContentType() != null) {
                        existingGstinDeatils.setGstindocContentType(gstinDeatils.getGstindocContentType());
                    }
                    if (gstinDeatils.getStatus() != null) {
                        existingGstinDeatils.setStatus(gstinDeatils.getStatus());
                    }

                    return existingGstinDeatils;
                }
            )
            .map(gstinDeatilsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gstinDeatils.getId().toString())
        );
    }

    */
/**
     * {@code GET  /gstin-deatils} : get all the gstinDeatils.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gstinDeatils in body.
     *//*

    @GetMapping("/gstin-deatils")
    public List<GstinDeatils> getAllGstinDeatils() {
        log.debug("REST request to get all GstinDeatils");
        return gstinDeatilsRepository.findAll();
    }

    */
/**
     * {@code GET  /gstin-deatils/:id} : get the "id" gstinDeatils.
     *
     * @param id the id of the gstinDeatils to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gstinDeatils, or with status {@code 404 (Not Found)}.
     *//*

    @GetMapping("/gstin-deatils/{id}")
    public ResponseEntity<GstinDeatils> getGstinDeatils(@PathVariable Long id) {
        log.debug("REST request to get GstinDeatils : {}", id);
        Optional<GstinDeatils> gstinDeatils = gstinDeatilsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(gstinDeatils);
    }

    */
/**
     * {@code DELETE  /gstin-deatils/:id} : delete the "id" gstinDeatils.
     *
     * @param id the id of the gstinDeatils to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     *//*

    @DeleteMapping("/gstin-deatils/{id}")
    public ResponseEntity<Void> deleteGstinDeatils(@PathVariable Long id) {
        log.debug("REST request to delete GstinDeatils : {}", id);
        gstinDeatilsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
*/
