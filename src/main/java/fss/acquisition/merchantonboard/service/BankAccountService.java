package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.*;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import fss.acquisition.merchantonboard.domain.verification.Bankverification;
import fss.acquisition.merchantonboard.repository.*;
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

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    AadharDetailsRepository aadharDetailsRepository;

    public String createBankAccount(BankAccount bankAccount) throws Exception {
        Business business = businessService.getBusinessbyMid(bankAccount.getMid());
        boolean exitsByMidAndAccountno = bankAccountRepository.existsByMidAndAccountno(bankAccount.getMid(), bankAccount.getAccountno());
        if (Boolean.TRUE == exitsByMidAndAccountno)
            throw new Exception("Mid and account Number are already registered ");
        if (business.getIdentityverification() == 1||business.getIdentityverification() == 3) {
            AadharDetails aadharDetails = aadharDetailsRepository.findByMid(bankAccount.getMid())
                    .orElseThrow(() -> new ResourseNotFoundException("Business Pan is not available for entered Mid " + bankAccount.getMid()));
            if (!(aadharDetails.getStatus().equals(Status.APPROVED)))
                throw new Exception("Aadhar must verify first ");
        }
        if (business.getBusinessverificationpan() == 1||business.getBusinessverificationpan() == 3) {
            BusinessPan businessPan = businessPanRepository.findByMid(bankAccount.getMid())
                    .orElseThrow(() -> new ResourseNotFoundException("Business Pan is not available for entered Mid " + bankAccount.getMid()));
            if (!(businessPan.getStatus().equals(Status.APPROVED)))
                throw new Exception("Business Pan must verify first ");
        }
        if (business.getBusinessverificationgstin() == 1||business.getBusinessverificationgstin() == 3) {
            GstinDetails gstinDetails = gstinDeatilsRepository.findByMid(bankAccount.getMid())
                    .orElseThrow(() -> new ResourseNotFoundException("GstinDeatils is not available for entered Mid " + bankAccount.getMid()));
            if (!(gstinDetails.getStatus().equals(Status.APPROVED)))
                throw new Exception("Gstin must verify first ");
        }
        bankAccount.setStatus(getStatus(bankAccount));
        bankAccountRepository.save(bankAccount);
        if (Status.APPROVED.equals(getStatus(bankAccount))) {
            business.setAccountverification(2);
            business.setStatus(Status.APPROVED);
        } else
            business.setAccountverification(3);
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
        Optional<Bankverification> bankverificationOptional = bankverificationRepository.findByBankverificationId(bankAccount.getAccountno());
        return bankverificationOptional.isPresent() ? Status.APPROVED : Status.DECLINED;
    }

}
