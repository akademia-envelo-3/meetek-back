package pl.envelo.meetek.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDto {

    private long surveyId;
    private String question;
    private Set<SurveyChoiceDto> choices;
    private int maxChoicesNumber;
    private long eventId;
    private Set<SurveyResponseDto> responses;

}
