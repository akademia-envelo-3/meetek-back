package pl.envelo.meetek.domain.coordinates;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CoordinatesService {

    private final CoordinatesRepo coordinatesRepo;
    private final CoordinatesValidator coordinatesValidator;

    @Transactional
    public Coordinates createCoordinates(Coordinates coordinates) {
        coordinatesValidator.validateInput(coordinates);
        return coordinatesRepo.save(coordinates);
    }

    @Transactional(readOnly = true)
    public Coordinates getCoordinatesById(long coordinatesId) {
        return coordinatesValidator.validateExists(coordinatesId);
    }

}