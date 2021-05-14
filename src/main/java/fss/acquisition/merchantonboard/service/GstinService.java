package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.*;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import fss.acquisition.merchantonboard.domain.verification.Gstinverification;
import fss.acquisition.merchantonboard.repository.*;
import fss.acquisition.merchantonboard.repository.verification.GstinverificationRepository;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GstinService {

    private final Logger log = LoggerFactory.getLogger(GstinService.class);


    @Autowired
    BusinessIncorporationService businessIncorporationService;

    @Autowired
    GstinDeatilsRepository gstinDeatilsRepository;

    @Autowired
    GstinverificationRepository gstinverificationRepository;

    @Autowired
    BusinessService businessService;

    @Autowired
    BusinessPanRepository businessPanRepository;

    @Autowired
    BusinessIncorporationRepository businessIncorporationRepository;

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    AadharDetailsRepository aadharDetailsRepository;

    public String createGstIn(GstinDetails gstinDetails) throws Exception {
       // BusinessIncorporation businessIncorporation = businessIncorporationService.getBusinessIncorporation(gstinDetails.getMid());
        boolean existsByMidAndGstinno = gstinDeatilsRepository.existsByMid(gstinDetails.getMid());
        if (Boolean.TRUE == existsByMidAndGstinno) {
            throw new Exception("Mid and GSTIN are already exists");
        }
        Business business = businessService.getBusinessbyMid(gstinDetails.getMid());
        if (!(business.getBusinessverificationgstin() == 1))
            throw new Exception("Gstin verification is not requires for entered mid " + gstinDetails.getMid());
        if (business.getIdentityverification() == 1||business.getIdentityverification() == 3) {
            AadharDetails aadharDetails = aadharDetailsRepository.findByMid(gstinDetails.getMid())
                    .orElseThrow(() -> new ResourseNotFoundException("Business Pan is not available for entered Mid " + gstinDetails.getMid()));
            if (!(aadharDetails.getStatus().equals(Status.APPROVED)))
                throw new Exception("Aadhar must verify first ");
        }
        if (business.getBusinessverificationpan() == 1||business.getBusinessverificationpan() == 3) {
            BusinessPan businessPan = businessPanRepository.findByMid(gstinDetails.getMid())
                    .orElseThrow(() -> new ResourseNotFoundException("Business Pan is not available for entered Mid " + gstinDetails.getMid()));
            if (!(businessPan.getStatus().equals(Status.APPROVED)))
                throw new Exception("Business Pan must verify first ");
        }
        Status status = getStatus(gstinDetails);
        gstinDetails.setStatus(status);
        gstinDeatilsRepository.save(gstinDetails);
     /*   businessIncorporation.setStatus(gstinDetails.getStatus());
        businessIncorporationRepository.save(businessIncorporation);
      */  if (Status.APPROVED.equals(status))
            business.setBusinessverificationgstin(2);
        else
            business.setBusinessverificationgstin(3);
        return String.valueOf(gstinDetails.getStatus());
    }

    public String updateGstin(GstinDetails gstinDetails) throws ResourseNotFoundException {
        BusinessIncorporation businessIncorporation = businessIncorporationService.getBusinessIncorporation(gstinDetails.getMid());
        GstinDetails gstinDetails1 = gstinDeatils(gstinDetails.getMid());
        gstinDetails1.setGstindoc(gstinDetails.getGstindoc());
        gstinDetails1.setGstinno(gstinDetails.getGstinno());
        gstinDetails1.setGstindocContentType(gstinDetails.getGstindocContentType());
        gstinDetails1.setStatus(getStatus(gstinDetails));
        gstinDeatilsRepository.save(gstinDetails);
        businessIncorporation.setStatus(gstinDetails.getStatus());
        businessIncorporationRepository.save(businessIncorporation);
        return String.valueOf(gstinDetails1.getStatus());
    }

    public GstinDetails getGstinDetails(String mid) throws ResourseNotFoundException {
        return gstinDeatils(mid);
    }

    public GstinDetails gstinDeatils(String mid) throws ResourseNotFoundException {
        GstinDetails gstinDetails = gstinDeatilsRepository.findByMid(mid)
                .orElseThrow(() -> new ResourseNotFoundException("Provided Merchant Id is not valid"));
        return gstinDetails;
    }


    public Status getStatus(GstinDetails gstinDetails) {
        Optional<Gstinverification> gstinverificationOptional = gstinverificationRepository.findByGstinverificationId(gstinDetails.getGstinno());
        return gstinverificationOptional.isPresent() ? Status.APPROVED : Status.DECLINED;
    }
}
