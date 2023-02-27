package pl.envelo.meetek.domain.survey.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    @Size(min = 10, max = 200, message = "Field must be between {min} and {max} characters")
    private String question;

    @ManyToMany
    @JoinTable(name = "surveys_x_choices",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "choice_id"))
    private List<SurveyChoice> choices;

    @NotNull(message = "Field must not be null")
    @Size(max = 10, message = "Can't be more than {max} choices")
    private int maxChoicesNumber;

    @NotNull(message = "Field must not be null")
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

    public Survey(Long surveyId, String question) {
        this.surveyId = surveyId;
        this.question = question;
    }

    public Survey(String question) {
        this.question = question;
    }

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
