package pl.envelo.meetek.repository.request;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.request.RequestStatus;

@Repository
public interface RequestStatusRepo extends CrudRepository<RequestStatus, Long> {
}
