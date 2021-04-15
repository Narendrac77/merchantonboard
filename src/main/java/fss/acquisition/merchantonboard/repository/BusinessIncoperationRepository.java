package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.BusinessIncoperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BusinessIncoperation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessIncoperationRepository extends JpaRepository<BusinessIncoperation, Long> {}
