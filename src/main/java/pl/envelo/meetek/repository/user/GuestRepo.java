package pl.envelo.meetek.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.user.Guest;

@Repository
public interface GuestRepo extends CrudRepository<Guest, Long> {
}
