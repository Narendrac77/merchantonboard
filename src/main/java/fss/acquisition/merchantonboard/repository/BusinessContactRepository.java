package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.BusinessContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data SQL repository for the BusinessContact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessContactRepository extends JpaRepository<BusinessContact, Long> {

        Optional<BusinessContact> findByMid(UUID mid);

        void deleteByMid(UUID mid);
}
