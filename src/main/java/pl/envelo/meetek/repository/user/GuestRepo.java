package pl.envelo.meetek.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.user.Guest;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Long> {
}
