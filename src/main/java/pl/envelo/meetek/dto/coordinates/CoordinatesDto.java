package pl.envelo.meetek.dto.coordinates;

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
    private float latitude;
    private float longitude;

}
