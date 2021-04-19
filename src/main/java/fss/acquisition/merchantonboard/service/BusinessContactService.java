package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.BankAccount;
import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.BusinessContact;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import fss.acquisition.merchantonboard.repository.BankAccountRepository;
import fss.acquisition.merchantonboard.repository.BusinessContactRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
public class BusinessContactService {

    private final Logger log = LoggerFactory.getLogger(BusinessContactService.class);

    @Autowired
    BusinessService businessService;

    @Autowired
    BusinessContactRepository businessContactRepository;

    public boolean createBusinessContact(BusinessContact businessContact) throws ResourseNotFoundException {
        Business businessbyMid = businessService.getBusinessbyMid(businessContact.getMid());
        if(!ObjectUtils.isEmpty(businessbyMid)){
            businessContactRepository.save(businessContact);
        }
        return ObjectUtils.isEmpty(businessbyMid)?Boolean.FALSE:Boolean.TRUE;
    }

    public boolean updateBusinessContact(BusinessContact businessContact) throws ResourseNotFoundException {
        BusinessContact businessContact1 = getBusinessContact(businessContact.getMid());
        if(!ObjectUtils.isEmpty(businessContact1)){
            businessContactRepository.save(businessContact);
        }
        return ObjectUtils.isEmpty(businessContact1)?Boolean.FALSE:Boolean.TRUE;
    }

    public BusinessContact getBusinessContact(UUID mid) throws ResourseNotFoundException {
        BusinessContact businessContact = businessContactByMid(mid);
        return !ObjectUtils.isEmpty(businessContact)?businessContact:null;
    }

    public BusinessContact businessContactByMid(UUID mid) throws ResourseNotFoundException {
        BusinessContact businessContact = businessContactRepository.findByMid(mid)
                .orElseThrow(()-> new ResourseNotFoundException("Provided Merchant Id is not valid"));
        return !ObjectUtils.isEmpty(businessContact)?businessContact:null;
    }

}
