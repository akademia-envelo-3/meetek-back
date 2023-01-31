package pl.envelo.meetek.repository.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.request.RequestStatus;
import pl.envelo.meetek.model.request.CategoryRequest;

import java.util.List;

@Repository
public interface CategoryRequestRepo extends JpaRepository<CategoryRequest, Long> {

    List<CategoryRequest> findAllByStatus(RequestStatus status);

}
