package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByMid(String mid);

    void deleteByMid(String mid);

    boolean existsByMidAndAccountno(String Mid, String AccountNo);


}
