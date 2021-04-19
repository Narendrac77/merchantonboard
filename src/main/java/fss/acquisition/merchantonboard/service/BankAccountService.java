package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.BankAccount;
import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import fss.acquisition.merchantonboard.repository.BankAccountRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class BankAccountService {

    private final Logger log = LoggerFactory.getLogger(BankAccountService.class);

    @Autowired
    BusinessService businessService;

    @Autowired
    BankAccountRepository bankAccountRepository;

    public boolean createBankAccount(BankAccount bankAccount) throws ResourseNotFoundException {
        Business businessbyMid = businessService.getBusinessbyMid(bankAccount.getMid());
        if(!ObjectUtils.isEmpty(businessbyMid)){   // TODO BANK VERIFICATION API
         bankAccountRepository.save(bankAccount);
        }
        return ObjectUtils.isEmpty(businessbyMid)?Boolean.FALSE:Boolean.TRUE;
    }

    public boolean updateBankAccount(BankAccount bankAccount) throws ResourseNotFoundException {
        BankAccount bankAccount1 = bankAccountByMid(bankAccount.getMid());
        if(!ObjectUtils.isEmpty(bankAccount1)){//TODO BANK VERIFICATION API
            bankAccountRepository.save(bankAccount);
        }
        return ObjectUtils.isEmpty(bankAccount1)?Boolean.FALSE:Boolean.TRUE;
    }

    public BankAccount getBankAccount(UUID mid) throws ResourseNotFoundException {
        BankAccount bankAccount1 = bankAccountByMid(mid);
        return !ObjectUtils.isEmpty(bankAccount1)?bankAccount1:null;
    }

    public BankAccount bankAccountByMid(UUID mid) throws ResourseNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findByMid(mid)
                .orElseThrow(()-> new ResourseNotFoundException("Provided Merchant Id is not valid"));
        return !ObjectUtils.isEmpty(bankAccount)?bankAccount:null;
    }


    public Status getStatus(BankAccount bankAccount){//TODO BANK VERIFICATION API
        return Status.APPROVED;
    }


}
