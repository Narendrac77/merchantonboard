package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.GstinDeatils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data SQL repository for the GstinDeatils entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GstinDeatilsRepository extends JpaRepository<GstinDeatils, Long> {

    Optional<GstinDeatils> findByMid(UUID mid);

    void deleteByMid(UUID mid);
}
