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

import java.util.Optional;
import java.util.UUID;

@Service
public class BusinessIncorporationService {

    private final Logger log = LoggerFactory.getLogger(BusinessIncorporationService.class);

    @Autowired
    BusinessService businessService;

    @Autowired
    BusinessIncorporationRepository businessIncorporationRepository;

    public void createBusinessIncorporation(BusinessIncorporation businessIncorporation) throws Exception {
        businessService.getBusinessbyMid(businessIncorporation.getMid());
        Optional<BusinessIncorporation> businessIncorporation1 = businessIncorporationRepository.findByMid(businessIncorporation.getMid());
        if(businessIncorporation1.isPresent())
            throw new Exception("Business Incorporation is already exits for entered mid "+ businessIncorporation.getMid());
          businessIncorporationRepository.save(businessIncorporation);
    }

    public void updateBusinessIncorporation(BusinessIncorporation businessIncorporation) throws ResourseNotFoundException {
        BusinessIncorporation businessIncorporation1 = getBusinessIncorporation(businessIncorporation.getMid());
        businessIncorporation1.setIncorporationno(businessIncorporation.getIncorporationno());
        businessIncorporation1.setIncorporationdoc(businessIncorporation.getIncorporationdoc());
        businessIncorporation1.setBusinessregisteredaddress(businessIncorporation.getBusinessregisteredaddress());
        businessIncorporation1.setIncorporationdocContentType(businessIncorporation.getIncorporationdocContentType());
        businessIncorporation1.setBusinesslegalname(businessIncorporation.getBusinesslegalname());
        businessIncorporationRepository.save(businessIncorporation1);
    }

    public BusinessIncorporation getBusinessIncorporation(String mid) throws ResourseNotFoundException {
        BusinessIncorporation businessIncorporation = businessIncorporationRepository.findByMid(mid)
                .orElseThrow(() -> new ResourseNotFoundException("Provided Merchant Id is not valid"));
        return !ObjectUtils.isEmpty(businessIncorporation) ? businessIncorporation : null;
    }

    }
