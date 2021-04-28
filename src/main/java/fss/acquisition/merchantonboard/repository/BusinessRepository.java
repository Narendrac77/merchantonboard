package fss.acquisition.merchantonboard.repository;

import fss.acquisition.merchantonboard.domain.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BusinessRepository extends JpaRepository<Business, String> {

    @Query(value = "Select * from Business where mid = ?1",nativeQuery = true)
    Optional<Business> findBusinessByMid(String mid);

    Optional<Business> findByBusinessid(Long id);

    void deleteByBusinessid(Long id);

    boolean existsByDisplayname(String displayName);

    Business findByDisplayname(String displayName);

    Business getByBusinessid(Long id);

    List<Business> findByMid(String mid);
    }
