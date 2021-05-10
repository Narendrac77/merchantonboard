package fss.acquisition.merchantonboard.repository.verification;


import fss.acquisition.merchantonboard.domain.verification.Bankverification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankverificationRepository extends JpaRepository<Bankverification, Integer> {

   // @Query(value = "SELECT * from BANKVERIFICATION WHERE bankverification_id = ?1",nativeQuery = true)
    Optional<Bankverification> findByBankverificationId(String id);
}
