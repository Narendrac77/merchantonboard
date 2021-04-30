package fss.acquisition.merchantonboard.service;

import fss.acquisition.merchantonboard.domain.Business;
import fss.acquisition.merchantonboard.domain.BusinessIncorporation;
import fss.acquisition.merchantonboard.domain.BusinessPan;
import fss.acquisition.merchantonboard.domain.GstinDeatils;
import fss.acquisition.merchantonboard.domain.enumeration.Status;
import fss.acquisition.merchantonboard.domain.verification.Gstinverification;
import fss.acquisition.merchantonboard.repository.BusinessIncorporationRepository;
import fss.acquisition.merchantonboard.repository.BusinessPanRepository;
import fss.acquisition.merchantonboard.repository.GstinDeatilsRepository;
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

    public String createGstIn(GstinDeatils gstinDeatils) throws Exception {
        BusinessIncorporation businessIncorporation = businessIncorporationService.getBusinessIncorporation(gstinDeatils.getMid());
        boolean existsByMidAndGstinno = gstinDeatilsRepository.existsByMidAndGstinno(gstinDeatils.getMid(), gstinDeatils.getGstinno());
        if(Boolean.TRUE==existsByMidAndGstinno){
            throw new Exception("Mid and GSTIN are already exists");
        }
        Business business = businessService.getBusinessbyMid(gstinDeatils.getMid());
        if (!(business.getBusinessverification() == 1))
            throw new Exception("Gstin verification is not requires for entered mid " + gstinDeatils.getMid());
        if(business.getIdentityverification()==1) {
            BusinessPan businessPan = businessPanRepository.findByMid(gstinDeatils.getMid())
                    .orElseThrow(() -> new ResourseNotFoundException("Business Pan is not available for entered Mid " + gstinDeatils.getMid()));
            if (!(businessPan.getStatus().equals(Status.APPROVED)))
                throw new Exception("Business Pan must verify first ");
        }
        Status status = getStatus(gstinDeatils);
        gstinDeatils.setStatus(status);
        gstinDeatilsRepository.save(gstinDeatils);
        businessIncorporation.setStatus(gstinDeatils.getStatus());
        businessIncorporationRepository.save(businessIncorporation);
        return String.valueOf(gstinDeatils.getStatus());
    }

    public String updateGstin(GstinDeatils gstinDeatils) throws ResourseNotFoundException {
        BusinessIncorporation businessIncorporation = businessIncorporationService.getBusinessIncorporation(gstinDeatils.getMid());
        GstinDeatils gstinDeatils1 = gstinDeatils(gstinDeatils.getMid());
        gstinDeatils1.setGstindoc(gstinDeatils.getGstindoc());
        gstinDeatils1.setGstinno(gstinDeatils.getGstinno());
        gstinDeatils1.setGstindocContentType(gstinDeatils.getGstindocContentType());
        gstinDeatils1.setStatus(getStatus(gstinDeatils));
        gstinDeatilsRepository.save(gstinDeatils);
        businessIncorporation.setStatus(gstinDeatils.getStatus());
        businessIncorporationRepository.save(businessIncorporation);
        return String.valueOf(gstinDeatils1.getStatus());
    }

    public GstinDeatils getGstinDetails(String mid) throws ResourseNotFoundException {
        return gstinDeatils(mid);
    }

    public GstinDeatils gstinDeatils(String mid) throws ResourseNotFoundException {
        GstinDeatils gstinDeatils = gstinDeatilsRepository.findByMid(mid)
                .orElseThrow(() -> new ResourseNotFoundException("Provided Merchant Id is not valid"));
        return gstinDeatils;
    }


    public Status getStatus(GstinDeatils gstinDeatils) {
        Optional<Gstinverification> gstinverificationOptional = gstinverificationRepository.findByGstinverificationId(Integer.valueOf(gstinDeatils.getGstinno()));
        return gstinverificationOptional.isPresent() ? Status.APPROVED : Status.DECLINED;    }
}
