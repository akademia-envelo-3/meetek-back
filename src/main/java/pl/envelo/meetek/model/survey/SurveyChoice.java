package pl.envelo.meetek.model.survey;

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
