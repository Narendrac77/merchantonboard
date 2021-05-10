package fss.acquisition.merchantonboard.service;

import com.sun.jndi.dns.ResourceRecord;
import fss.acquisition.merchantonboard.domain.BusinessOwner;
import fss.acquisition.merchantonboard.repository.BusinessOwnerRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class BusinessOwnerService {

    private final Logger log = LoggerFactory.getLogger(BusinessOwnerService.class);

    @Autowired
    BusinessOwnerRepository businessOwnerRepository;

    public boolean createBusinessOwner(BusinessOwner businessOwner) throws ResourseNotFoundException {
        boolean flag = false;
      Optional<BusinessOwner> businessOwner1 = businessOwnerByMobileNo(businessOwner.getMobileno());
      if(!businessOwner1.isPresent()){
          businessOwnerRepository.save(businessOwner);
          flag = true;
          return flag;
        }
      return flag;
    }

    public boolean updateBusinessOwner(BusinessOwner businessOwner) throws ResourseNotFoundException {
        boolean flag = false;
        Optional<BusinessOwner> businessOwner1 = businessOwnerByMobileNo(businessOwner.getMobileno());
        if(!businessOwner1.isPresent()){
            businessOwnerRepository.save(businessOwner);
            flag = true;
            return flag;
        }
        return flag;
    }

    public BusinessOwner getBusinessOwner(String mobileNumber) throws ResourseNotFoundException {
        Optional<BusinessOwner> businessOwner1 = businessOwnerByMobileNo(mobileNumber);
        return !businessOwner1.isPresent() ? businessOwner1.get(): null;
        }


    public Optional<BusinessOwner> businessOwnerByMobileNo(String mobileNumber) throws ResourseNotFoundException {
        Optional<BusinessOwner> businessOwner = businessOwnerRepository.findByMobileno(mobileNumber);
        return businessOwner;
    }

    public BusinessOwner getBusinessOwnerbyId(Long id) throws ResourseNotFoundException {
        BusinessOwner businessOwner = businessOwnerRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("No resource found for this id"));
    return  businessOwner;
    }

}
