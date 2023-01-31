package pl.envelo.meetek.model.survey;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.model.user.AppUser;
import pl.envelo.meetek.model.user.StandardUser;

import java.util.Objects;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "survey_responses_x_answers",
            joinColumns = @JoinColumn(name = "response_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private Set<SurveyChoice> answers;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private StandardUser user;

    @Override
    public String toString() {
        return "SurveyResponse{" +
                "responseId=" + responseId +
                ", answers=" + answers +
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
