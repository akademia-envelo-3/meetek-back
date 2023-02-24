package pl.envelo.meetek.domain.survey.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "survey_responses")
public class SurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long responseId;
    @NotEmpty(message = "Answers can not be empty")
    @ManyToMany
    @JoinTable(name = "survey_responses_x_answers",
            joinColumns = @JoinColumn(name = "response_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private Set<SurveyChoice> answers;

    @NotNull(message = "Field must not be null")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private StandardUser user;

    public SurveyResponse(Long responseId) {
        this.responseId = responseId;
    }

    public SurveyResponse(Set<SurveyChoice> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        List<SurveyChoice> sortedAnswers = new ArrayList<>(answers);
        sortedAnswers.sort(Comparator.comparingLong(SurveyChoice::getChoiceId));
        return "SurveyResponse{" +
                "responseId=" + responseId +
                ", answers=" + sortedAnswers +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveyResponse that = (SurveyResponse) o;
        return Objects.equals(responseId, that.responseId) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseId, user);
    }
}
