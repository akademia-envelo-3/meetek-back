package pl.envelo.meetek.model.survey;

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
@Table(name = "survey_choices")
public class SurveyChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long choiceId;
    private String description;

    @Override
    public String toString() {
        return "SurveyChoice{" +
                "choiceId=" + choiceId +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveyChoice that = (SurveyChoice) o;
        return Objects.equals(choiceId, that.choiceId) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(choiceId, description);
    }
}
