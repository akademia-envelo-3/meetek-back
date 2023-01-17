package pl.envelo.meetek.repository.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.request.CategoryRequest;

@Repository
public interface CategoryRequestRepo extends JpaRepository<CategoryRequest, Long> {
}
