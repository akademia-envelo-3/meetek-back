package pl.envelo.meetek.domain.survey.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyCreateDto {

    private String question;
    private List<SurveyChoiceDto> choices;
    private int maxChoicesNumber;

}
