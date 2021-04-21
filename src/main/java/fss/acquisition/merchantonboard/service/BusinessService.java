package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.dao.BusinessDao;
import fss.acquisition.merchantonboard.dao.enumeration.RiskEnum;
import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.BusinessOwner;
import fss.acquisition.merchantonboard.domain.metadata.Categories;
import fss.acquisition.merchantonboard.repository.BusinessRepository;
import fss.acquisition.merchantonboard.repository.metadata.CategoriesRepository;
import fss.acquisition.merchantonboard.repository.metadata.SubCategoriesRepository;
import fss.acquisition.merchantonboard.web.rest.errors.GlobalExceptionHandler;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
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
        UUID getUUid = generateUUid();
        Integer categoriesId = getCategoriesId(Integer.valueOf(business.getBusinesscategory()));
        getSubCategoriesId(Integer.valueOf(business.getBusinesssubcategory()));
        business.setMid(getUUid);
        RiskEnum riskEnum = generateRisk(categoriesId, business.getTurnover());
        business.setRiskscoring(riskEnum);
        businessRepository.save(business);
        BusinessDao businessDao = getBusinessDao(getUUid.toString(),riskEnum);
        return businessDao;
    }

    public BusinessDao updateBusiness(Business business) throws ResourseNotFoundException {
        Business business1 = businessRepository.findByMid(business.getMid())
                .orElseThrow(() -> new ResourseNotFoundException("MerchantId  not found for " + business.getMid()));
        Integer categoriesId = getCategoriesId(Integer.valueOf(business.getBusinesscategory()));
        RiskEnum riskEnum = generateRisk(categoriesId, business.getTurnover());
        getSubCategoriesId(Integer.valueOf(business.getBusinesssubcategory()));
        business1.setRiskscoring(riskEnum);
        business1.setTurnover(business.getTurnover());
        business1.setAge(business.getAge());
        business1.setBusinesstype(business.getBusinesstype());
        business1.setCommunicationaddress(business.getCommunicationaddress());
        business1.setBusinesscategory(business.getBusinesscategory());
        business1.setBusinesssubcategory(business.getBusinesssubcategory());
        businessRepository.save(business1);
        BusinessDao businessDao = getBusinessDao(String.valueOf(business.getMid()),riskEnum);
        return businessDao;
    }


    public Business getbusiness(Long id) throws ResourseNotFoundException {
        Business business1 = businessRepository.findByBusinessid(id)
                .orElseThrow(() -> new ResourseNotFoundException("Resource not found"));
        return !ObjectUtils.isEmpty(business1) ? business1 : null;
    }

    public Business getBusinessbyMid(UUID mid) throws ResourseNotFoundException {
        log.debug("Business fetched by ", mid);
        Optional<Business> businessOptional = Optional.ofNullable(businessRepository.findByMid(mid)
                .orElseThrow(() -> new ResourseNotFoundException("Business resourse is not found for entered id")));
        return businessOptional.get();
    }

    private UUID generateUUid() {
        return UUID.randomUUID();
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

    public RiskEnum generateRisk(Integer mccCode, BigInteger turnOver) {
        RiskEnum riskEnum = null;
        if (mccCode.equals(4814) && (turnOver.longValue()>=10000000))
            riskEnum = RiskEnum.HIGHRISK;
        if (mccCode.equals(5641) && ((turnOver.longValue()>=100000) && (turnOver.longValue()<10000000)))
            riskEnum = RiskEnum.MEDIUMRISK;
        if (mccCode.equals(5641) && ((turnOver.longValue()>0) && (turnOver.longValue()<100000)))
            riskEnum = RiskEnum.LOWRISK;
        return riskEnum;
    }

    public BusinessDao getBusinessDao(String mid,RiskEnum riskEnum){
        BusinessDao businessDao = new BusinessDao();
        businessDao.setMid(mid);
        businessDao.setRiskEnum(riskEnum);
        return businessDao;
    }
}

