package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.BusinessPan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BusinessPanRepository extends JpaRepository<BusinessPan, Long> {

    Optional<BusinessPan> findByMid(UUID mid);

    void deleteByMid(UUID mid);
}
