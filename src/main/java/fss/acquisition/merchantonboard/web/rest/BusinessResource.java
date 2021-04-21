package fss.acquisition.merchantonboard.web.rest;

import fss.acquisition.merchantonboard.dao.BusinessDao;
import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.repository.BusinessRepository;
import fss.acquisition.merchantonboard.service.BusinessService;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    BusinessService businessService;

    private BusinessRepository businessRepository;

    public BusinessResource(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }



    @PostMapping("/businesses")
    public BusinessDao createBusiness(@RequestBody Business business) throws Exception {
        log.debug("REST request to save Business : {}", business);
        BusinessDao businessDao = businessService.createBusiness(business);
        return businessDao;
        }

    @PutMapping("/businesses/{id}")
    public BusinessDao updateBusiness(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Business business
    ) throws ResourseNotFoundException {
        log.debug("REST request to update Business : {}, {}", id, business);
        BusinessDao businessDao = businessService.updateBusiness(business);
        return businessDao ;

    }



  /*  @PatchMapping(value = "/businesses/{id}", consumes = "application/merge-patch+json")
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
    }*/


    @GetMapping("/businesses")
    public List<Business> getAllBusinesses() {
        log.debug("REST request to get all Businesses");
        return businessRepository.findAll();
    }
    @GetMapping("/businesses/{id}")
    public Business getBusiness(@PathVariable Long id) throws ResourseNotFoundException {
        log.debug("REST request to get Business : {}", id);
        return  businessService.getbusiness(id);

    }

  @DeleteMapping("/businesses/{id}")
    public String deleteBusiness(@PathVariable Long id) {
        log.debug("REST request to delete Business : {}", id);
        businessRepository.deleteByBusinessid(id);
        return "Deleted Successfully";
    }
}
