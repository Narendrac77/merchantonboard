package fss.acquisition.merchantonboard.repository.metadata;

import fss.acquisition.merchantonboard.domain.metadata.Subcategories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoriesRepository extends JpaRepository<Subcategories,Integer> {

    Optional<Subcategories> findBySubcategorieid(Integer id);

    boolean existsSubcategoriesBySubcategorieid(Integer id);
}
