package pl.envelo.meetek.domain.coordinates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesDto {

    private long coordinatesId;
    private double latitude;
    private double longitude;

    public CoordinatesDto(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
