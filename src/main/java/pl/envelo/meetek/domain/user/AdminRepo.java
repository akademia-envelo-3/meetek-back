package pl.envelo.meetek.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.domain.user.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
}
