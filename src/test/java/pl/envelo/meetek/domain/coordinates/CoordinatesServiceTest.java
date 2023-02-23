package pl.envelo.meetek.domain.coordinates;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.category.*;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.DtoMapperService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoordinatesServiceTest {

    @Mock
    private CoordinatesRepo coordinatesRepo;

    @Mock
    private CoordinatesValidator coordinatesValidator;

    @Mock
    private DtoMapperService mapperService;

    @InjectMocks
    private CoordinatesService coordinatesService;

    @Test
    void testGetCoordinatesById_ResultSuccess() {
        long coordinatesId = 1L;
        Coordinates coordinates = new Coordinates();

        when(coordinatesValidator.validateExists(coordinatesId)).thenReturn(coordinates);

        assertNotNull(coordinates);
        Coordinates result = coordinatesService.getCoordinatesById(coordinatesId);

        verify(coordinatesValidator).validateExists(coordinatesId);
    }

    @Test
    void testGetCoordinatesById_ResultNotFoundException() {
        long coordinatesId = 1L;
        String message = "Category not found";

        when(coordinatesValidator.validateExists(coordinatesId)).thenThrow(new NotFoundException(message));

        assertThrows(NotFoundException.class, () -> coordinatesService.getCoordinatesById(coordinatesId));

        verify(coordinatesValidator).validateExists(coordinatesId);
    }

    @Test
    public void testCreateCoordinates() {
        double latitude = 12.34;
        double longitude = 56.78;

        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(latitude);
        coordinates.setLongitude(longitude);

        doNothing().when(coordinatesValidator).validateInput(coordinates);
        when(coordinatesRepo.save(coordinates)).thenReturn(coordinates);

        coordinatesService = new CoordinatesService(coordinatesRepo, coordinatesValidator);

        Coordinates result = coordinatesService.createCoordinates(coordinates);

        assertEquals(coordinates, result);

        verify(coordinatesValidator).validateInput(coordinates);
        verify(coordinatesRepo).save(coordinates);
    }

}