package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.BusinessIncorporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data SQL repository for the BusinessIncoperation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessIncorporationRepository extends JpaRepository<BusinessIncorporation, Long> {

    Optional<BusinessIncorporation> findByMid(UUID Mid);

    void deleteByMid(UUID mid);
}
