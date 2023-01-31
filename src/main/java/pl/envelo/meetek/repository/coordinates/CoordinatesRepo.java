package pl.envelo.meetek.repository.coordinates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.coordinates.Coordinates;

@Repository
public interface CoordinatesRepo extends JpaRepository<Coordinates, Long> {
}
