package fss.acquisition.merchantonboard.repository;


import fss.acquisition.merchantonboard.domain.AadharDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the AadharDetails entity.
 */
@Repository
public interface AadharDetailsRepository extends JpaRepository<AadharDetails, Long> {

    Optional<AadharDetails> findByBusinessid(Long id);

    void deleteBybusinessid(Long id);
}
