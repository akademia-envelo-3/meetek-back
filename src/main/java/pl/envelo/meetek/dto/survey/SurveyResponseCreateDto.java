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
public class SurveyResponseCreateDto {

    private Set<SurveyChoiceDto> answers;

}
