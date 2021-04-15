package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.BusinessOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BusinessOwner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessOwnerRepository extends JpaRepository<BusinessOwner, Long> {}
