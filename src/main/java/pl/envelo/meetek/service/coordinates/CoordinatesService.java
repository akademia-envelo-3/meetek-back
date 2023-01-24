package pl.envelo.meetek.service.coordinates;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.coordinates.Coordinates;
import pl.envelo.meetek.repository.coordinates.CoordinatesRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CoordinatesService {

    private final CoordinatesRepo coordinatesRepo;

    @Transactional
    public Coordinates createCoordinates(Coordinates coordinates) {
        return coordinatesRepo.save(coordinates);
    }

    @Transactional(readOnly = true)
    public Optional<Coordinates> getCoordinatesById(long coordinatesId) {
        return coordinatesRepo.findById(coordinatesId);
    }

}