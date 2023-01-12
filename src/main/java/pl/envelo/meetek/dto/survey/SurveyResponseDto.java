package pl.envelo.meetek.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.dto.user.StandardUserShortDto;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponseDto {

    private long responseId;
    private Set<SurveyChoiceDto> answers;
    private StandardUserShortDto user;

}
