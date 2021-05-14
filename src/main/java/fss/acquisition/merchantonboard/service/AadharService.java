package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.AadharDetails;
import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import fss.acquisition.merchantonboard.repository.AadharDetailsRepository;
import fss.acquisition.merchantonboard.repository.BusinessRepository;
import fss.acquisition.merchantonboard.repository.verification.AadharverificationRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AadharService {

    private final Logger log = LoggerFactory.getLogger(BusinessOwnerService.class);

    @Autowired
    AadharDetailsRepository aadharDetailsRepository;

    @Autowired
    BusinessService businessService;

    @Autowired
    AadharverificationRepository aadharverificationRepository;

    @Autowired
    BusinessRepository businessRepository;

    public String createAadharDetails(AadharDetails aadharDetails) throws Exception {
        Business business = businessService.getBusinessbyMid(aadharDetails.getMid());
        if (!(business.getIdentityverification() == 1))
            throw new Exception("Aadhar Verification is not required");
        aadharValidation(aadharDetails.getAadharno());
        Status aadharStatus = getAadharStatus(aadharDetails.getAadharno());
        aadharDetails.setStatus(aadharStatus);
        aadharDetailsRepository.save(aadharDetails);
        if (Status.APPROVED.equals(aadharStatus))
            business.setIdentityverification(2);
        else
            business.setIdentityverification(3);
        businessRepository.save(business);
        return String.valueOf(aadharStatus);
    }

    public boolean updateAadharDetails(AadharDetails aadharDetails) throws ResourseNotFoundException {
      /*  AadharDetails aadharDetails1 = aadharDetailsRepository.findByBusinessid(aadharDetails.getBusinessid())
                .orElseThrow(() -> new ResourseNotFoundException("ResourseNotFound"));*/
        boolean flag = false;
      /*  if (!ObjectUtils.isEmpty(aadharDetails1)) {
            aadharDetailsRepository.save(aadharDetails);
            flag = true;
            return flag;
        }*/
        return flag;
    }

    public AadharDetails getAadharDetailsById(Long id) throws ResourseNotFoundException {
        /*AadharDetails aadharDetails = aadharDetailsRepository.findByBusinessid(id)
                .orElseThrow(() -> new ResourseNotFoundException("ResourseNotFound"));
        return !ObjectUtils.isEmpty(aadharDetails) ? aadharDetails : null;*/
        return null;
    }

    public void aadharValidation(String aadharNo) throws Exception {
        if (aadharDetailsRepository.existsByAadharno(aadharNo))
            throw new Exception("Aadhar is already exists");
    }

    public Status getAadharStatus(String aadharNo) {
        return aadharverificationRepository.existsByAadharverificationid(aadharNo) ? Status.APPROVED : Status.DECLINED;
    }

}
