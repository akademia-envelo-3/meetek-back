package pl.envelo.meetek.repository.category;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.category.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findAll(Sort sort);

    List<Category> findAllByIsActiveTrueOrderByName();

    Optional<Category> findByName(String name);

}
