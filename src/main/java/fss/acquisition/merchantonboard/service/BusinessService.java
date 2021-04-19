package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.repository.BusinessRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BusinessService {

    private final Logger log = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    BusinessRepository businessRepository;

    public Business getBusinessbyMid(UUID mid) throws ResourseNotFoundException {
        log.debug("Business fetched by ",mid);
        Optional<Business> businessOptional = Optional.ofNullable(businessRepository.findByMid(mid)
                .orElseThrow(() -> new ResourseNotFoundException("Business resourse is not found for entered id")));
        return businessOptional.get();
    }

}

