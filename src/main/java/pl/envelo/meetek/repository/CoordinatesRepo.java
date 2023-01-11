package pl.envelo.meetek.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.Coordinates;

@Repository
public interface CoordinatesRepo extends CrudRepository<Coordinates, Long> {
}
