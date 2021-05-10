package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.BusinessPan;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import fss.acquisition.merchantonboard.domain.verification.Panverification;
import fss.acquisition.merchantonboard.repository.BusinessPanRepository;
import fss.acquisition.merchantonboard.repository.BusinessRepository;
import fss.acquisition.merchantonboard.repository.verification.PanverificationRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessPanService {
    private final Logger log = LoggerFactory.getLogger(BusinessPanService.class);

    @Autowired
    BusinessService businessService;

    @Autowired
    BusinessPanRepository businessPanRepository;

    @Autowired
    PanverificationRepository panverificationRepository;

    @Autowired
    BusinessRepository businessRepository;

    public String createBusinessPan(BusinessPan businessPan) throws Exception {
        Business business = businessService.getBusinessbyMid(businessPan.getMid());
        if (!(business.getIdentityverification() == 1))
            throw new Exception("Pan Verification is not required");
        businesspanCheck(businessPan.getMid());
        businessPan.setStatus(getStatus(businessPan));
        businessPanRepository.save(businessPan);
        if (Status.APPROVED.equals(getStatus(businessPan)))
            business.setIdentityverification(2);
        else
            business.setIdentityverification(3);
        businessRepository.save(business);
        return String.valueOf(businessPan.getStatus());
    }


    public String updateBusinessPan(BusinessPan businessPan) throws Exception {
        Business business = businessService.getBusinessbyMid(businessPan.getMid());
        BusinessPan businessPan1 = businessPanByMid(businessPan.getMid());
        if (!(business.getIdentityverification() == 1))
            throw new Exception("Pan Verification is not required");
        businessPan1.setPandoc(businessPan.getPandoc());
        businessPan1.setPanno(businessPan.getPanno());
        businessPan1.setStatus(getStatus(businessPan));
        businessPanRepository.save(businessPan1);
        return String.valueOf(businessPan1.getStatus());
    }

    public BusinessPan getBusinessPan(String mid) throws ResourseNotFoundException {
        BusinessPan businessPanByMid = businessPanByMid(mid);
        return businessPanByMid;
    }

    public BusinessPan businessPanByMid(String mid) throws ResourseNotFoundException {
        BusinessPan businessPan = businessPanRepository.findByMid(mid)
                .orElseThrow(() -> new ResourseNotFoundException("Provided Merchant Id is not valid"));
        return businessPan;
    }


    public Status getStatus(BusinessPan businessPan) {
        Optional<Panverification> panverificationOptional = panverificationRepository.findByPanverificationId(businessPan.getPanno());
        return panverificationOptional.isPresent() ? Status.APPROVED : Status.DECLINED;
    }


    public void businesspanCheck(String mid) throws Exception {
        Optional<BusinessPan> businessPan = businessPanRepository.findByMid(mid);
        if (businessPan.isPresent()) {
            throw new Exception("business Id is already exists " + mid);
        }
    }
}
