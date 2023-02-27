package pl.envelo.meetek.domain.coordinates;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.user.StandardUserRepo;
import pl.envelo.meetek.domain.user.StandardUserValidator;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoordinatesValidatorTest {
    @Mock
    private CoordinatesRepo coordinatesRepo;

    @InjectMocks
    private CoordinatesValidator coordinatesValidator;

    @Test
    void testValidate_WhenCoordinatesExists() {
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinatesId(1L);

        when(coordinatesRepo.findById(1L)).thenReturn(Optional.of(coordinates));
        CoordinatesValidator validator = new CoordinatesValidator(null, coordinatesRepo);

        Coordinates validatedcoordinates = validator.validateExists(1L);

        assertEquals(coordinates, validatedcoordinates);
    }

}