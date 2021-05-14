package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.dao.BusinessDao;
import fss.acquisition.merchantonboard.dao.BusinessStatusDao;
import fss.acquisition.merchantonboard.dao.VerifiactionStatus;
import fss.acquisition.merchantonboard.dao.VerificationCheck;
import fss.acquisition.merchantonboard.dao.enumeration.RiskEnum;
import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import fss.acquisition.merchantonboard.domain.metadata.Categories;
import fss.acquisition.merchantonboard.repository.BusinessRepository;
import fss.acquisition.merchantonboard.repository.metadata.CategoriesRepository;
import fss.acquisition.merchantonboard.repository.metadata.SubCategoriesRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BusinessService {

    private final Logger log = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    BusinessOwnerService businessOwnerService;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    SubCategoriesRepository subCategoriesRepository;

    public BusinessDao createBusiness(Business business) throws Exception {
        businessOwnerService.getBusinessOwnerbyId(business.getBusinessid());
        displayNameCheck(business.getDisplayname());
        String getUUid = generateUUid();
        Integer mccCode = getCategoriesId(Integer.valueOf(business.getBusinesscategory()));
        getSubCategoriesId(Integer.valueOf(business.getBusinesssubcategory()));
        business.setMid(getUUid);
        RiskEnum riskEnum = generateRisk(mccCode, business.getTurnover());
        business.setRiskscoring(riskScoringNumber(riskEnum));
        business.setStatus(Status.PROCESSING);
        getVerificationPriority(business);
        businessRepository.save(business);
        BusinessDao businessDao = getBusinessDao(business, riskEnum);
        return businessDao;
    }

    public BusinessDao updateBusiness(Business business) throws Exception {
     /* //  Business business1 = businessRepository.findBusinessByMid(business.getMid())
      //          .orElseThrow(() -> new ResourseNotFoundException("MerchantId  not found for " + business.getMid()));
        Integer mccCode = getCategoriesId(Integer.valueOf(business.getBusinesscategory()));
        RiskEnum riskEnum = generateRisk(mccCode, business.getTurnover());
        getSubCategoriesId(Integer.valueOf(business.getBusinesssubcategory()));
        business1.setRiskscoring(riskScoringNumber(riskEnum));
        business1.setTurnover(business.getTurnover());
        business1.setAge(business.getAge());
        business1.setBusinesstype(business.getBusinesstype());
        business1.setCommunicationaddress(business.getCommunicationaddress());
        business1.setBusinesscategory(business.getBusinesscategory());
        business1.setBusinesssubcategory(business.getBusinesssubcategory());
        getVerificationPriority(business1);
        businessRepository.save(business1);
        BusinessDao businessDao = getBusinessDao(business1,riskEnum);
        return businessDao;*/
        return new BusinessDao();
    }


    public Business getbusiness(Long id) throws ResourseNotFoundException {
        Business business1 = businessRepository.findByBusinessid(id)
                .orElseThrow(() -> new ResourseNotFoundException("Resource not found"));
        return !ObjectUtils.isEmpty(business1) ? business1 : null;
    }

    public Business getBusinessbyMid(String mid) throws ResourseNotFoundException {
        log.debug("Business fetched by ", mid);
        Business business = businessRepository.findBusinessByMid(mid)
                .orElseThrow(() -> new ResourseNotFoundException("Business resourse is not found for entered id"));
        return business;
    }

    private String generateUUid() {
        return String.valueOf(UUID.randomUUID());
    }

    private Integer getCategoriesId(Integer id) throws ResourseNotFoundException {
        if (!categoriesRepository.existsCategoriesByCategorieid(id)) {
            throw new ResourseNotFoundException("categorie not found for enterted id" + id);
        }
        Optional<Categories> categoriesOptional = categoriesRepository.findByCategorieid(id);
        Categories categories = categoriesOptional.get();
        return categories.getMcccode();
    }

    private boolean getSubCategoriesId(Integer id) throws ResourseNotFoundException {
        boolean flag = true;
        if (!subCategoriesRepository.existsSubcategoriesBySubcategorieid(id)) {
            throw new ResourseNotFoundException("subcategorie not found for enterted id" + id);
        }
        return flag;
    }

    private boolean displayNameCheck(String displayName) throws Exception {
        boolean flag = true;
        if (businessRepository.existsByDisplayname(displayName)) {
            throw new Exception("Display Name already exists");
        }
        return flag;
    }

    public RiskEnum generateRisk(Integer mccCode, BigInteger turnOver) throws Exception {
        RiskEnum riskEnum = null;
        if (mccCode.equals(1) || (turnOver.longValue() >= 10000000)) {
            if (!(turnOver.longValue() >= 10000000))
                throw new Exception("turnover must be above 1crore");
            riskEnum = RiskEnum.HIGHRISK;
        } else if (mccCode.equals(2) || (turnOver.longValue() >= 100000)) {
            if (!((turnOver.longValue() >= 100000) && (turnOver.longValue() < 10000000)))
                throw new Exception("turnover must be above 1lakh and below 1crore");
            riskEnum = RiskEnum.MEDIUMRISK;
        } else if (mccCode.equals(3)) {
            if (!((turnOver.longValue() > 0) && (turnOver.longValue() < 100000)))
                throw new Exception("turnover must be below 1lakh");
            riskEnum = RiskEnum.LOWRISK;
        }
        return riskEnum;
    }

    public BusinessDao getBusinessDao(Business business, RiskEnum riskEnum) {
        BusinessDao businessDao = new BusinessDao();
        businessDao.setMid(String.valueOf(business.getMid()));
        businessDao.setRiskEnum(riskEnum);
        VerificationCheck verificationCheck = new VerificationCheck();
        verificationCheck.setIdentityVerification(business.getIdentityverification());
        verificationCheck.setBusinessVerificationpan(business.getBusinessverificationpan());
        verificationCheck.setBusinessVerificationgstin(business.getBusinessverificationgstin());
        verificationCheck.setAccountVerification(business.getAccountverification());
        businessDao.setVerificationCheck(verificationCheck);
        return businessDao;
    }

    public Business getVerificationPriority(Business business) {
        business.setAccountverification(1);
        if (business.getRiskscoring() == 1) {
            business.setBusinessverificationpan(1);
            business.setBusinessverificationgstin(1);
            business.setIdentityverification(1);
        }
        if (business.getRiskscoring() == 2) {
            business.setBusinessverificationpan(0);
            business.setBusinessverificationgstin(0);
            business.setIdentityverification(1);
        }
        if (business.getRiskscoring() == 3) {
            business.setBusinessverificationpan(0);
            business.setBusinessverificationgstin(0);
            business.setIdentityverification(0);
        }
        return business;
    }

    public Integer riskScoringNumber(RiskEnum riskEnum) {
        Integer riskNumber = null;
        if (riskEnum.equals(RiskEnum.HIGHRISK))
            riskNumber = 1;
        if (riskEnum.equals(RiskEnum.MEDIUMRISK))
            riskNumber = 2;
        if (riskEnum.equals(RiskEnum.LOWRISK))
            riskNumber = 3;
        return riskNumber;
    }

    public BusinessStatusDao getBusinessStatus(String mid) throws ResourseNotFoundException {
        Business business = businessRepository.findBusinessByMid(mid)
                .orElseThrow(() -> new ResourseNotFoundException("Resource not for entered mid " + mid));
        BusinessStatusDao businessStatusDao = new BusinessStatusDao();
        VerifiactionStatus verifiactionStatus = new VerifiactionStatus();
        if (business.getIdentityverification() == 1)
            verifiactionStatus.setIdentityVerification(String.valueOf(Status.PROCESSING));
        else if (business.getIdentityverification() == 2)
            verifiactionStatus.setIdentityVerification(String.valueOf(Status.APPROVED));
        else if (business.getIdentityverification() == 3)
            verifiactionStatus.setIdentityVerification(String.valueOf(Status.DECLINED));
        if (business.getBusinessverificationpan() == 1)
            verifiactionStatus.setBusinessverificationpan(String.valueOf(Status.PROCESSING));
        else if (business.getBusinessverificationpan() == 2)
            verifiactionStatus.setBusinessverificationpan(String.valueOf(Status.APPROVED));
        else if (business.getBusinessverificationpan() == 3)
            verifiactionStatus.setBusinessverificationpan(String.valueOf(Status.DECLINED));
        if (business.getBusinessverificationgstin() == 1)
            verifiactionStatus.setBusinessverificationgstin(String.valueOf(Status.PROCESSING));
        else if (business.getBusinessverificationgstin() == 2)
            verifiactionStatus.setBusinessverificationgstin(String.valueOf(Status.APPROVED));
        else if (business.getBusinessverificationgstin() == 3)
            verifiactionStatus.setBusinessverificationgstin(String.valueOf(Status.DECLINED));
        if (business.accountverification == 1)
            verifiactionStatus.setAccountVerification(String.valueOf(Status.PROCESSING));
        else if (business.accountverification == 2)
            verifiactionStatus.setAccountVerification(String.valueOf(Status.APPROVED));
        else if (business.accountverification == 3)
            verifiactionStatus.setAccountVerification(String.valueOf(Status.DECLINED));
        businessStatusDao.setVerifiactionStatus(verifiactionStatus);
        businessStatusDao.setBankStatus(business.getStatus());
        return businessStatusDao;
    }


}

