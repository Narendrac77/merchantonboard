package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.BankAccount;
import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.BusinessPan;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import fss.acquisition.merchantonboard.repository.BankAccountRepository;
import fss.acquisition.merchantonboard.repository.BusinessPanRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
public class BusinessPanService {
    private final Logger log = LoggerFactory.getLogger(BusinessPanService.class);

    @Autowired
    BusinessService businessService;

    @Autowired
    BusinessPanRepository businessPanRepository;

    public boolean createBusinessPan(BusinessPan businessPan) throws ResourseNotFoundException {
        Business businessbyMid = businessService.getBusinessbyMid(businessPan.getMid());
        if(!ObjectUtils.isEmpty(businessbyMid)){   // TODO PAN VERIFICATION API
            businessPanRepository.save(businessPan);
        }
        return ObjectUtils.isEmpty(businessbyMid)?Boolean.FALSE:Boolean.TRUE;
    }

    public boolean updateBusinessPan(BusinessPan businessPan) throws ResourseNotFoundException {
        BusinessPan businessPan1 = businessPanByMid(businessPan.getMid());
        if(!ObjectUtils.isEmpty(businessPan1)){//TODO PAN VERIFICATION API
            businessPanRepository.save(businessPan);
        }
        return ObjectUtils.isEmpty(businessPan1)?Boolean.FALSE:Boolean.TRUE;
    }

    public BusinessPan getBusinessPan(UUID mid) throws ResourseNotFoundException {
        BusinessPan businessPanByMid = businessPanByMid(mid);
        return !ObjectUtils.isEmpty(businessPanByMid)?businessPanByMid:null;
    }

    public BusinessPan businessPanByMid(UUID mid) throws ResourseNotFoundException {
        BusinessPan businessPan = businessPanRepository.findByMid(mid)
                .orElseThrow(()-> new ResourseNotFoundException("Provided Merchant Id is not valid"));
        return !ObjectUtils.isEmpty(businessPan)?businessPan:null;
    }


    public Status getStatus(BusinessPan businessPan){//TODO PAN VERIFICATION API
        return Status.APPROVED;
    }
}
