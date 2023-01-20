package pl.envelo.meetek.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.Coordinates;
import pl.envelo.meetek.repository.CoordinatesRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CoordinatesService {

    private final CoordinatesRepo coordinatesRepo;

    public Coordinates createCoordinates(Coordinates coordinates) {
        return coordinatesRepo.save(coordinates);
    }

    public Optional<Coordinates> getCoordinatesById(long coordinatesId) {
        return coordinatesRepo.findById(coordinatesId);
    }
}