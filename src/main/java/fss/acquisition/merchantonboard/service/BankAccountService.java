package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.BankAccount;
import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.BusinessPan;
import fss.acquisition.merchantonboard.domain.GstinDeatils;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import fss.acquisition.merchantonboard.domain.verification.Bankverification;
import fss.acquisition.merchantonboard.repository.BankAccountRepository;
import fss.acquisition.merchantonboard.repository.BusinessPanRepository;
import fss.acquisition.merchantonboard.repository.GstinDeatilsRepository;
import fss.acquisition.merchantonboard.repository.verification.BankverificationRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class BankAccountService {

    private final Logger log = LoggerFactory.getLogger(BankAccountService.class);

    @Autowired
    BusinessService businessService;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    BankverificationRepository bankverificationRepository;

    @Autowired
    BusinessPanRepository businessPanRepository;

    @Autowired
    GstinDeatilsRepository gstinDeatilsRepository;

    public String createBankAccount(BankAccount bankAccount) throws Exception {
        Business business = businessService.getBusinessbyMid(bankAccount.getMid());
        boolean exitsByMidAndAccountno = bankAccountRepository.existsByMidAndAccountno(bankAccount.getMid(), bankAccount.getAccountno());
        if(Boolean.TRUE==exitsByMidAndAccountno)
            throw  new Exception("Mid and account Number are already registered ");
        if(business.getIdentityverification()==1) {
            BusinessPan businessPan = businessPanRepository.findByMid(bankAccount.getMid())
                    .orElseThrow(() -> new ResourseNotFoundException("Business Pan is not available for entered Mid " + bankAccount.getMid()));
            if (!(businessPan.getStatus().equals(Status.APPROVED)))
                throw new Exception("Business Pan must verify first ");
        }
        if (business.getBusinessverification() == 1) {
            GstinDeatils gstinDeatils = gstinDeatilsRepository.findByMid(bankAccount.getMid())
                    .orElseThrow(() -> new ResourseNotFoundException("GstinDeatils is not available for entered Mid " + bankAccount.getMid()));
            if (!(gstinDeatils.getStatus().equals(Status.APPROVED)))
                throw new Exception("Gstin must verify first ");
        }
            bankAccount.setStatus(getStatus(bankAccount));
            bankAccountRepository.save(bankAccount);
            return String.valueOf(bankAccount.getStatus());
        }

    public String updateBankAccount(BankAccount bankAccount) throws ResourseNotFoundException {
        BankAccount bankAccount1 = bankAccountByMid(bankAccount.getMid());
        bankAccount1.setAccountno(bankAccount.getAccountno());
        bankAccount1.setBankname(bankAccount.getBankname());
        bankAccount1.setIfsccode(bankAccount.getIfsccode());
        bankAccount1.setStatus(getStatus(bankAccount));
        bankAccountRepository.save(bankAccount1);
        return String.valueOf(bankAccount1.getStatus());
        }



    public BankAccount getBankAccount(String mid) throws ResourseNotFoundException {
        BankAccount bankAccount1 = bankAccountByMid(mid);
        return !ObjectUtils.isEmpty(bankAccount1) ? bankAccount1 : null;
    }

    public BankAccount bankAccountByMid(String mid) throws ResourseNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findByMid(mid)
                .orElseThrow(() -> new ResourseNotFoundException("Provided Merchant Id is not valid"));
        return !ObjectUtils.isEmpty(bankAccount) ? bankAccount : null;
    }


    public Status getStatus(BankAccount bankAccount) {
        Optional<Bankverification> bankverificationOptional = bankverificationRepository.findByBankverificationId(Integer.valueOf(bankAccount.getAccountno()));
        return bankverificationOptional.isPresent() ? Status.APPROVED : Status.DECLINED;
    }

}
