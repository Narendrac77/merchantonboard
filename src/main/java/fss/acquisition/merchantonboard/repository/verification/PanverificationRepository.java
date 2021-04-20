package fss.acquisition.merchantonboard.repository.verification;

import fss.acquisition.merchantonboard.domain.verification.Panverification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PanverificationRepository extends JpaRepository<Panverification, Integer> {

 Optional<Panverification> findByPanverificationId(Integer id);

}
