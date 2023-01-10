package pl.envelo.meetek.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Coordinates {

    private Long coordinatesId;
    private float latitude;
    private float longitude;
}
