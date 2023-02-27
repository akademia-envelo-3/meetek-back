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

    private double latitude;

    private double longitude;

    public void setLongitude(double longitude) {
        this.longitude = (float) longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = (float) latitude;
    }

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

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
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && Objects.equals(coordinatesId, that.coordinatesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinatesId, latitude, longitude);
    }
}
