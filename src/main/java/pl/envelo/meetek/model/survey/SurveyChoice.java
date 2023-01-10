package pl.envelo.meetek.model.survey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SurveyChoice {

    private Long choiceId;
    private String description;

}
