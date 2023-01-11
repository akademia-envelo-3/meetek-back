package pl.envelo.meetek.model.survey;

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
public class SurveyChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long choiceId;
    private String description;

}
