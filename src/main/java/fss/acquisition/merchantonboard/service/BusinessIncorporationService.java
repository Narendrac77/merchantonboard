package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.BusinessIncorporation;
import fss.acquisition.merchantonboard.repository.BusinessIncorporationRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
public class BusinessIncorporationService{

    private final Logger log = LoggerFactory.getLogger(BusinessIncorporationService.class);

    @Autowired
    BusinessService businessService;

    @Autowired
    BusinessIncorporationRepository businessIncorporationRepository;

    public boolean createBusinessIncorporation(BusinessIncorporation businessIncorporation) throws ResourseNotFoundException {
        Business businessbyMid = businessService.getBusinessbyMid(businessIncorporation.getMid());
        if(!ObjectUtils.isEmpty(businessbyMid)){
            businessIncorporationRepository.save(businessIncorporation);
        }
        return ObjectUtils.isEmpty(businessbyMid)?Boolean.FALSE:Boolean.TRUE;
    }

    public boolean updateBusinessIncorporation(BusinessIncorporation businessIncorporation) throws ResourseNotFoundException {
        BusinessIncorporation businessIncorporation1 = getBusinessIncorporation(businessIncorporation.getMid());
        if(!ObjectUtils.isEmpty(businessIncorporation1)){
            businessIncorporationRepository.save(businessIncorporation);
        }
        return !ObjectUtils.isEmpty(businessIncorporation1)?Boolean.FALSE:Boolean.TRUE;
    }

    public BusinessIncorporation getBusinessIncorporation(UUID mid) throws ResourseNotFoundException {
        BusinessIncorporation businessIncorporation = businessIncoperationByMid(mid);
        return ObjectUtils.isEmpty(businessIncorporation)? businessIncorporation :null;
    }

    public BusinessIncorporation businessIncoperationByMid(UUID mid) throws ResourseNotFoundException {
        BusinessIncorporation businessIncorporation = businessIncorporationRepository.findByMid(mid)
                .orElseThrow(()-> new ResourseNotFoundException("Provided Merchant Id is not valid"));
        return !ObjectUtils.isEmpty(businessIncorporation)? businessIncorporation :null;
    }
}
