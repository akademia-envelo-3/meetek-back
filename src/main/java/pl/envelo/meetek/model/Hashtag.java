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
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long hashtagId;
    private String name;
    private boolean isActive;
    private int countOfHashtagUsage;

}
