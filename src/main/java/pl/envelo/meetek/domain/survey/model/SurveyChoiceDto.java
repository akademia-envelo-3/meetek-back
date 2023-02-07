package pl.envelo.meetek.domain.survey.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyChoiceDto {

    private long choiceId;
    private String description;

}
