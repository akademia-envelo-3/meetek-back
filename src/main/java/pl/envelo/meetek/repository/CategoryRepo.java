package pl.envelo.meetek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.Category;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderByName();

}
