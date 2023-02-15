package pl.envelo.meetek.domain.coordinates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "coordinates")
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
