package pl.envelo.meetek.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.CoordinatesRepo;

@AllArgsConstructor
@Service
public class CoordinatesService {

    private final CoordinatesRepo coordinatesRepo;
}
