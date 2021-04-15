package fss.acquisition.merchantonboard.repository;


import fss.acquisition.merchantonboard.domain.AadharDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AadharDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AadharDetailsRepository extends JpaRepository<AadharDetails, Long> {}
