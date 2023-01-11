package pl.envelo.meetek.repository.request;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.request.RequestBox;

@Repository
public interface RequestBoxRepo extends CrudRepository<RequestBox, Long> {
}
