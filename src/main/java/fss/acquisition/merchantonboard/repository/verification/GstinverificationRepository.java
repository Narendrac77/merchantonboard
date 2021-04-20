package fss.acquisition.merchantonboard.repository.verification;


import fss.acquisition.merchantonboard.domain.verification.Gstinverification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GstinverificationRepository extends JpaRepository<Gstinverification, Integer> {

    Optional<Gstinverification> findByGstinverificationId(Integer id);
}
