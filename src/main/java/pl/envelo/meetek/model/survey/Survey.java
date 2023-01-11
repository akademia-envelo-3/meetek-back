package pl.envelo.meetek.model.survey;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.event.Event;

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

}
