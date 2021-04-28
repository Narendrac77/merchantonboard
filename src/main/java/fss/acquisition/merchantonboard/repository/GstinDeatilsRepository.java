package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.GstinDeatils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GstinDeatilsRepository extends JpaRepository<GstinDeatils, Long> {

    Optional<GstinDeatils> findByMid(String mid);

    void deleteByMid(String mid);
}
