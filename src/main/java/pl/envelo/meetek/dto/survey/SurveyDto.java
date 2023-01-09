package pl.envelo.meetek.dto.survey;

import java.util.Set;

public class SurveyDto {

    private long surveyId;
    private String question;
    private Set<SurveyChoiceDto> choices;
    private int maxChoicesNumber;
    private long eventId;
    private Set<SurveyResponseDto> responses;

}
