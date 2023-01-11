package pl.envelo.meetek.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.Category;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {
}
