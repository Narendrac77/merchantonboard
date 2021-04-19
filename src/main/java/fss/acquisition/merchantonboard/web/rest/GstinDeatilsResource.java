package fss.acquisition.merchantonboard.web.rest;

import fss.acquisition.merchantonboard.domain.GstinDeatils;
import fss.acquisition.merchantonboard.repository.GstinDeatilsRepository;
import fss.acquisition.merchantonboard.service.GstinService;
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
public class GstinDeatilsResource {

    private final Logger log = LoggerFactory.getLogger(GstinDeatilsResource.class);

    private static final String ENTITY_NAME = "gstinDeatils";

    @Autowired
    GstinService gstinService;

    private final GstinDeatilsRepository gstinDeatilsRepository;

    public GstinDeatilsResource(GstinDeatilsRepository gstinDeatilsRepository) {
        this.gstinDeatilsRepository = gstinDeatilsRepository;
    }



    @PostMapping("/gstin-deatils")
    public String createGstinDeatils(@Valid @RequestBody GstinDeatils gstinDeatils) throws ResourseNotFoundException {
        log.debug("REST request to save GstinDeatils : {}", gstinDeatils);
        gstinService.updateGstin(gstinDeatils);
        return "created successfully";
         }


    @PutMapping("/gstin-deatils/{mid}")
    public String updateGstinDeatils(
        @PathVariable(value = "mid", required = true) final UUID mid,
        @Valid @RequestBody GstinDeatils gstinDeatils
    ) throws ResourseNotFoundException {
        log.debug("REST request to update GstinDeatils : {}, {}", mid, gstinDeatils);
        gstinService.updateGstin(gstinDeatils);
        return "Updated Successfully";
    }



  /*  @PatchMapping(value = "/gstin-deatils/{id}", consumes = "application/merge-patch+json")
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
    }*/


    @GetMapping("/gstin-deatils")
    public List<GstinDeatils> getAllGstinDeatils() {
        log.debug("REST request to get all GstinDeatils");
        return gstinDeatilsRepository.findAll();
    }


    @GetMapping("/gstin-deatils/{mid}")
    public GstinDeatils getGstinDeatils(@PathVariable UUID mid) throws ResourseNotFoundException {
        log.debug("REST request to get GstinDeatils : {}", mid);
        return gstinService.getGstinDetails(mid);
    }

    @DeleteMapping("/gstin-deatils/{mid}")
    public String deleteGstinDeatils(@PathVariable UUID mid) {
        log.debug("REST request to delete GstinDeatils : {}", mid);
        gstinDeatilsRepository.deleteByMid(mid);
        return " resource Deleted";
    }
}
