package fss.acquisition.merchantonboard.web.rest;

import fss.acquisition.merchantonboard.domain.AadharDetails;
import fss.acquisition.merchantonboard.repository.AadharDetailsRepository;
import fss.acquisition.merchantonboard.service.AadharService;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.apache.tomcat.util.http.HeaderUtil;
import org.apache.tomcat.util.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AadharDetailsResource {

    private final Logger log = LoggerFactory.getLogger(AadharDetailsResource.class);

    private static final String ENTITY_NAME = "aadharDetails";

    @Autowired
    AadharService aadharService;

    private final AadharDetailsRepository aadharDetailsRepository;

    public AadharDetailsResource(AadharDetailsRepository aadharDetailsRepository) {
        this.aadharDetailsRepository = aadharDetailsRepository;
    }


    @PostMapping("/aadhar-details")
    public String createAadharDetails(@Valid @RequestBody AadharDetails aadharDetails) throws URISyntaxException, ResourseNotFoundException {
        log.debug("REST request to save AadharDetails : {}", aadharDetails);
        aadharService.createAadharDetails(aadharDetails);
        return "Created Successfully";
    }

    /**
     * {@code PUT  /aadhar-details/:id} : Updates an existing aadharDetails.
     *
     * @param id            the id of the aadharDetails to save.
     * @param aadharDetails the aadharDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aadharDetails,
     * or with status {@code 400 (Bad Request)} if the aadharDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the aadharDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/aadhar-details/{id}")
    public String updateAadharDetails(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody AadharDetails aadharDetails
    ) throws ResourseNotFoundException {
        aadharService.updateAadharDetails(aadharDetails);
        return "Updated Successfully";
    }

    /**
     * {@code PATCH  /aadhar-details/:id} : Partial updates given fields of an existing aadharDetails, field will ignore if it is null
     *
     * @param id            the id of the aadharDetails to save.
     * @param aadharDetails the aadharDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aadharDetails,
     * or with status {@code 400 (Bad Request)} if the aadharDetails is not valid,
     * or with status {@code 404 (Not Found)} if the aadharDetails is not found,
     * or with status {@code 500 (Internal Server Error)} if the aadharDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
   /* @PatchMapping(value = "/aadhar-details/{id}", consumes = "application/merge-patch+json")
    public String partialUpdateAadharDetails(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @RequestBody AadharDetails aadharDetails
    ) throws URISyntaxException {
        log.debug("REST request to partial update AadharDetails partially : {}, {}", id, aadharDetails);


        Optional<AadharDetails> result = aadharDetailsRepository
                .findById(aadharDetails.getId())
                .map(
                        existingAadharDetails -> {
                            if (aadharDetails.getAadharno() != null) {
                                existingAadharDetails.setAadharno(aadharDetails.getAadharno());
                            }
                            if (aadharDetails.getAadhardoc() != null) {
                                existingAadharDetails.setAadhardoc(aadharDetails.getAadhardoc());
                            }
                            if (aadharDetails.getAadhardocContentType() != null) {
                                existingAadharDetails.setAadhardocContentType(aadharDetails.getAadhardocContentType());
                            }
                            if (aadharDetails.getStatus() != null) {
                                existingAadharDetails.setStatus(aadharDetails.getStatus());
                            }

                            return existingAadharDetails;
                        }
                )
                .map(aadharDetailsRepository::save);

        return null;
    }*/

    @GetMapping("/aadhar-details")
    public List<AadharDetails> getAllAadharDetails() {
        log.debug("REST request to get all AadharDetails");
         return  aadharDetailsRepository.findAll();
    }


    @GetMapping("/aadhar-details/{id}")
    public AadharDetails getAadharDetails(@PathVariable Long id) throws ResourseNotFoundException {
        log.debug("REST request to get AadharDetails : {}", id);
        return aadharService.getAadharDetailsById(id);
    }


    @DeleteMapping("/aadhar-details/{id}")
    public String deleteAadharDetails(@PathVariable Long id) {
        log.debug("REST request to delete AadharDetails : {}", id);
        aadharDetailsRepository.deleteBybusinessid(id);
        return "";
    }
}
