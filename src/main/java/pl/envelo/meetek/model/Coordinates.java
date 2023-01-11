package pl.envelo.meetek.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}
