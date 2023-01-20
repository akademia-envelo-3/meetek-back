package pl.envelo.meetek.model.survey;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.user.AppUser;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class SurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long responseId;
    @ManyToMany
    private Set<SurveyChoice> answers;
    @ManyToOne
    private AppUser user;

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
        return Objects.equals(responseId, that.responseId) && Objects.equals(answers, that.answers) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseId, answers, user);
    }
}
