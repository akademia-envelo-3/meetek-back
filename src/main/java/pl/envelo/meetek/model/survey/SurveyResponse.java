package pl.envelo.meetek.model.survey;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.user.AppUser;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class SurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long responseId;
    @OneToMany
    private Set<SurveyChoice> answers;
    @ManyToOne
    private AppUser user;

}
