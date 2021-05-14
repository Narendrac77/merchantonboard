package fss.acquisition.merchantonboard.repository.verification;

import fss.acquisition.merchantonboard.domain.verification.Aadharverification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AadharverificationRepository extends JpaRepository<Aadharverification,Integer> {

    boolean existsByAadharverificationid(String aadhar);
}
