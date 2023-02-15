package pl.envelo.meetek.domain.survey.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    @Size(min = 2, max = 100, message = "Field must be between {min} and {max} characters")
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
