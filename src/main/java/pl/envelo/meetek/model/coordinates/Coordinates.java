package pl.envelo.meetek.model.coordinates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Coordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long coordinatesId;
    private float latitude;
    private float longitude;

    @Override
    public String toString() {
        return "Coordinates{" +
                "coordinatesId=" + coordinatesId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.latitude, latitude) == 0 && Float.compare(that.longitude, longitude) == 0 && Objects.equals(coordinatesId, that.coordinatesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinatesId, latitude, longitude);
    }
}
