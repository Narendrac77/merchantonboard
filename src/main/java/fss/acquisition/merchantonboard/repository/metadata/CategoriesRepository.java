package fss.acquisition.merchantonboard.repository.metadata;

import fss.acquisition.merchantonboard.domain.metadata.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Integer> {

    Optional<Categories> findByCategorieid(Integer id);

    boolean existsCategoriesByCategorieid(Integer id);

}
