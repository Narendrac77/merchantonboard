package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.BusinessOwner;
import fss.acquisition.merchantonboard.repository.BusinessRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class BusinessService {

    private final Logger log = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    BusinessOwnerService businessOwnerService;

    public boolean createBusiness(Business business) throws ResourseNotFoundException {
        BusinessOwner businessOwner = businessOwnerService.getBusinessOwnerbyId(business.getBusinessid());
        boolean flag = false;
        if(!ObjectUtils.isEmpty(businessOwner)){
            businessRepository.save(business);
            flag = true;
            return  flag;
        }
        return flag;
    }

    public boolean updateBusiness(Business business) throws ResourseNotFoundException{
        Business business1 = businessRepository.findByBusinessid(business.getBusinessid())
                .orElseThrow(() -> new ResourseNotFoundException("Resource not found"));
        boolean flag = false;
        if(!ObjectUtils.isEmpty(business1)){
            businessRepository.save(business);
            flag = true;
            return  flag;
        }
        return flag;
    }


   public Business getbusiness(Long id) throws ResourseNotFoundException {
       Business business1 = businessRepository.findByBusinessid(id)
               .orElseThrow(() -> new ResourseNotFoundException("Resource not found"));
    return !ObjectUtils.isEmpty(business1)?business1:null;
    }

    public Business getBusinessbyMid(UUID mid) throws ResourseNotFoundException {
        log.debug("Business fetched by ",mid);
        Optional<Business> businessOptional = Optional.ofNullable(businessRepository.findByMid(mid)
                .orElseThrow(() -> new ResourseNotFoundException("Business resourse is not found for entered id")));
        return businessOptional.get();
    }

    //public Business getBusinessbyMid

}

