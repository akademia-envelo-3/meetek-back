package pl.envelo.meetek.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.user.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
}
