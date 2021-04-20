package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.BusinessOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessOwnerRepository extends JpaRepository<BusinessOwner, Long> {

    boolean existsByMobileno(String mobileNo);

    Optional<BusinessOwner> findByMobileno(String mobileNo);

    void deleteByMobileno(String mobileNo);

}
