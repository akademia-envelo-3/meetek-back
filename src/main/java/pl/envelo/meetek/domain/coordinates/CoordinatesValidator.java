package pl.envelo.meetek.domain.coordinates;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class CoordinatesValidator extends ValidatorService<Coordinates> {

    private final CoordinatesRepo coordinatesRepo;

    public CoordinatesValidator(Validator validator, CoordinatesRepo coordinatesRepo) {
        super(validator);
        this.coordinatesRepo = coordinatesRepo;
    }

    @Override
    public Coordinates validateExists(long id) {
        Optional<Coordinates> coordinates = coordinatesRepo.findById(id);
        if (coordinates.isEmpty()) {
            throw new NotFoundException("Coordinates with id " + id + " not found");
        }
        return coordinates.get();
    }

}
