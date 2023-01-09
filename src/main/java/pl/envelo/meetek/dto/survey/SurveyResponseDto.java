package pl.envelo.meetek.dto.survey;

import pl.envelo.meetek.dto.user.StandardUserShortDto;

import java.util.Set;

public class SurveyResponseDto {

    private long responseId;
    private Set<SurveyChoiceDto> answers;
    private StandardUserShortDto user;

}
