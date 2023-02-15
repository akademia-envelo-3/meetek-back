package pl.envelo.meetek.domain.survey.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.user.model.StandardUserDto;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponseDto {

    private long responseId;
    private Set<SurveyChoiceDto> answers;
    private StandardUserDto user;

}
