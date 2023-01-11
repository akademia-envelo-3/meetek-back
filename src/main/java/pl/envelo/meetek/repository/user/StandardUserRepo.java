package pl.envelo.meetek.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.user.StandardUser;

@Repository
public interface StandardUserRepo extends CrudRepository<StandardUser, Long> {
}
