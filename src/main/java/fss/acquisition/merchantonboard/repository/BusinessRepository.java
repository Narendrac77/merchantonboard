package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    Optional<Business> findByMid(UUID mid);

    Optional<Business> findByBusinessid(Long id);

    void deleteByBusinessid(Long id);

}
