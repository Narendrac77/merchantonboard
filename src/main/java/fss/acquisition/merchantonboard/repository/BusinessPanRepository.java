package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.BusinessPan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BusinessPan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessPanRepository extends JpaRepository<BusinessPan, Long> {}
