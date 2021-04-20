package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.AadharDetails;
import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.BusinessOwner;
import fss.acquisition.merchantonboard.repository.AadharDetailsRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class AadharService {

    private final Logger log = LoggerFactory.getLogger(BusinessOwnerService.class);

    @Autowired
    AadharDetailsRepository aadharDetailsRepository;

    @Autowired
    BusinessOwnerService businessOwnerService;

    public boolean createAadharDetails(AadharDetails aadharDetails) throws ResourseNotFoundException {
        BusinessOwner businessOwner = businessOwnerService.getBusinessOwnerbyId(aadharDetails.getBusinessid());
        boolean flag = false;
        if (!ObjectUtils.isEmpty(businessOwner)) {
            aadharDetailsRepository.save(aadharDetails);
            flag = true;
            return flag;
        }
        return flag;
    }

    public boolean updateAadharDetails(AadharDetails aadharDetails) throws ResourseNotFoundException {
        AadharDetails aadharDetails1 = aadharDetailsRepository.findByBusinessid(aadharDetails.getBusinessid())
                .orElseThrow(() -> new ResourseNotFoundException("ResourseNotFound"));
        boolean flag = false;
        if (!ObjectUtils.isEmpty(aadharDetails1)) {
            aadharDetailsRepository.save(aadharDetails);
            flag = true;
            return flag;
        }
        return flag;
    }

    public AadharDetails getAadharDetailsById(Long id) throws ResourseNotFoundException {
        AadharDetails aadharDetails = aadharDetailsRepository.findByBusinessid(id)
                .orElseThrow(() -> new ResourseNotFoundException("ResourseNotFound"));
        return !ObjectUtils.isEmpty(aadharDetails) ? aadharDetails : null;
    }
}
