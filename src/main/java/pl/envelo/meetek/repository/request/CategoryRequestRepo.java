package pl.envelo.meetek.repository.request;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.request.CategoryRequest;

@Repository
public interface CategoryRequestRepo extends CrudRepository<CategoryRequest, Long> {
}
