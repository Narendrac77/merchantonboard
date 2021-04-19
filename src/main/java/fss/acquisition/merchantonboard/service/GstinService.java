package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.BusinessIncorporation;
import fss.acquisition.merchantonboard.domain.BusinessPan;
import fss.acquisition.merchantonboard.domain.GstinDeatils;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import fss.acquisition.merchantonboard.repository.BusinessPanRepository;
import fss.acquisition.merchantonboard.repository.BusinessRepository;
import fss.acquisition.merchantonboard.repository.GstinDeatilsRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class GstinService {

    private final Logger log = LoggerFactory.getLogger(GstinService.class);


    @Autowired
    BusinessIncorporationService businessIncorporationService;

    @Autowired
    GstinDeatilsRepository gstinDeatilsRepository;

    public boolean createGstIn(GstinDeatils gstinDeatils) throws ResourseNotFoundException {
        BusinessIncorporation businessIncorporation = businessIncorporationService.getBusinessIncorporation(gstinDeatils.getMid());
        if(!ObjectUtils.isEmpty(businessIncorporation)){   // TODO PAN VERIFICATION API
            gstinDeatilsRepository.save(gstinDeatils);
        }
        return ObjectUtils.isEmpty(gstinDeatils)?Boolean.FALSE:Boolean.TRUE;
    }

    public boolean updateGstin(GstinDeatils gstinDeatils) throws ResourseNotFoundException {
        GstinDeatils gstinDeatils1 = gstinDeatils(gstinDeatils.getMid());
        if(!ObjectUtils.isEmpty(gstinDeatils1)){//TODO PAN VERIFICATION API
            gstinDeatilsRepository.save(gstinDeatils);
        }
        return ObjectUtils.isEmpty(gstinDeatils)?Boolean.FALSE:Boolean.TRUE;
    }

    public GstinDeatils getGstinDetails(UUID mid) throws ResourseNotFoundException {
        GstinDeatils gstinDeatils = gstinDeatils(mid);
        return !ObjectUtils.isEmpty(gstinDeatils)?gstinDeatils:null;
    }

    public GstinDeatils gstinDeatils(UUID mid) throws ResourseNotFoundException {
        GstinDeatils gstinDeatils = gstinDeatilsRepository.findByMid(mid)
                .orElseThrow(()-> new ResourseNotFoundException("Provided Merchant Id is not valid"));
        ;
        return !ObjectUtils.isEmpty(gstinDeatils)?gstinDeatils:null;
    }


    public Status getStatus(BusinessPan businessPan){//TODO PAN VERIFICATION API
        return Status.APPROVED;
    }






}
