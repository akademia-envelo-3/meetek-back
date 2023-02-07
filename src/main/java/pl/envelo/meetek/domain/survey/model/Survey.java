package pl.envelo.meetek.domain.survey.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.event.model.SingleEvent;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long surveyId;
    private String question;

    @ManyToMany
    @JoinTable(name = "surveys_x_choices",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "choice_id"))
    private List<SurveyChoice> choices;
    private int maxChoicesNumber;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private SingleEvent event;

    @OneToMany
    @JoinTable(name = "surveys_x_responses",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "response_id"))
    private Set<SurveyResponse> responses;

    @Transient
    private Map<Long, BigDecimal> choicePercent;

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
        return maxChoicesNumber == survey.maxChoicesNumber && Objects.equals(surveyId, survey.surveyId) && Objects.equals(question, survey.question) && Objects.equals(event, survey.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surveyId, question, maxChoicesNumber, event);
    }

}
