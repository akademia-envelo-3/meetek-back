package pl.envelo.meetek.domain.coordinates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinatesTest {

    @Test
    void testToString() {
        Coordinates coordinates = new Coordinates(1L, 40.0f, -73.0f);
        String expectedString = "Coordinates{coordinatesId=1, latitude=40.0, longitude=-73.0}";

        assertEquals(expectedString, coordinates.toString());
    }

    @Test
    void testEquals() {
        Coordinates coordinates1 = new Coordinates();
        // Set the properties of the first coordinates object here...
        Coordinates coordinates2 = new Coordinates();
        // Set the properties of the second coordinates object here...
        assertEquals(coordinates1, coordinates2);
    }

    @Test
    void testHashCode() {
        Coordinates coordinates1 = new Coordinates(1L, 40.0f, -73.0f);
        Coordinates coordinates2 = new Coordinates(1L, 40.0f, -73.0f);

        assertEquals(coordinates1.hashCode(), coordinates2.hashCode());
    }
}
