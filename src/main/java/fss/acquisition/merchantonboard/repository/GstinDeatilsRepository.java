package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.GstinDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GstinDeatilsRepository extends JpaRepository<GstinDetails, Long> {

    Optional<GstinDetails> findByMid(String mid);

    void deleteByMid(String mid);

    boolean existsByMid(String mid);
}
