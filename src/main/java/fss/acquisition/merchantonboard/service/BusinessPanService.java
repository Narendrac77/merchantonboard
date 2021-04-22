package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.BankAccount;
import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.BusinessPan;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import fss.acquisition.merchantonboard.domain.verification.Panverification;
import fss.acquisition.merchantonboard.repository.BankAccountRepository;
import fss.acquisition.merchantonboard.repository.BusinessPanRepository;
import fss.acquisition.merchantonboard.repository.BusinessRepository;
import fss.acquisition.merchantonboard.repository.verification.PanverificationRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;

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
        if(!(business.getIdentityverification()==1))
        throw  new Exception("Pan Verification is not required");
            businessPan.setStatus(getStatus(businessPan));
            businessPanRepository.save(businessPan);
            return String.valueOf(businessPan.getStatus());
        }


    public String updateBusinessPan(BusinessPan businessPan) throws Exception {
        Business business = businessService.getBusinessbyMid(businessPan.getMid());
        BusinessPan businessPan1 = businessPanByMid(businessPan.getMid());
        if(!(business.getIdentityverification()==1))
            throw  new Exception("Pan Verification is not required");
            businessPan1.setPandoc(businessPan.getPandoc());
            businessPan1.setPanno(businessPan.getPanno());
            businessPan1.setStatus(getStatus(businessPan));
            businessPanRepository.save(businessPan1);
        return String.valueOf(businessPan1.getStatus());
    }

    public BusinessPan getBusinessPan(UUID mid) throws ResourseNotFoundException {
        BusinessPan businessPanByMid = businessPanByMid(mid);
        return businessPanByMid;
    }

    public BusinessPan businessPanByMid(UUID mid) throws ResourseNotFoundException {
        BusinessPan businessPan = businessPanRepository.findByMid(mid)
                .orElseThrow(()-> new ResourseNotFoundException("Provided Merchant Id is not valid"));
        return businessPan;
    }


    public Status getStatus(BusinessPan businessPan){
        return status(businessPan.getPanno());
    }

    public Status status(String panNumber){
        Optional<Panverification> panverificationOptional = panverificationRepository.findByPanverificationId(Integer.valueOf(panNumber));
        return panverificationOptional.isPresent()?Status.APPROVED:Status.DECLINED;
    }
}
