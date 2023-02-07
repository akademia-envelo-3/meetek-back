package pl.envelo.meetek.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.domain.user.model.Guest;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Long> {
}
