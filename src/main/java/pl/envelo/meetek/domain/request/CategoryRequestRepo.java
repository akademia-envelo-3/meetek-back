package pl.envelo.meetek.domain.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.domain.request.model.CategoryRequest;
import pl.envelo.meetek.domain.request.model.RequestStatus;

import java.util.List;

@Repository
public interface CategoryRequestRepo extends JpaRepository<CategoryRequest, Long> {

    List<CategoryRequest> findAllByStatus(RequestStatus status);

    List<CategoryRequest> findAllByStatusAndName(RequestStatus status, String name);

}
