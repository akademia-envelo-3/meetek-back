package pl.envelo.meetek.model.survey;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.event.Event;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long surveyId;
    private String question;
    @ManyToMany
    private Set<SurveyChoice> choices;
    private int maxChoicesNumber;
    @ManyToOne
    private Event event;
    @OneToMany
    private Set<SurveyResponse> responses;

    @Override
    public String toString() {
        return "Survey{" +
                "surveyId=" + surveyId +
                ", question='" + question + '\'' +
                ", choices=" + choices +
                ", maxChoicesNumber=" + maxChoicesNumber +
                ", event=" + event +
                ", responses=" + responses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Survey survey = (Survey) o;
        return maxChoicesNumber == survey.maxChoicesNumber && Objects.equals(surveyId, survey.surveyId) && Objects.equals(question, survey.question) && Objects.equals(choices, survey.choices) && Objects.equals(event, survey.event) && Objects.equals(responses, survey.responses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surveyId, question, choices, maxChoicesNumber, event, responses);
    }
}
