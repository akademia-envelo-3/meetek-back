package pl.envelo.meetek.domain.coordinates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatesRepo extends JpaRepository<Coordinates, Long> {
}
