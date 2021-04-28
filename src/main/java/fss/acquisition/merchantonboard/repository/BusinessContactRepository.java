package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.BusinessContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BusinessContactRepository extends JpaRepository<BusinessContact, Long> {

        Optional<BusinessContact> findByMid(String mid);

        void deleteByMid(String mid);
}
