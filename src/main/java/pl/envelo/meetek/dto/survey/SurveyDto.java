package pl.envelo.meetek.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDto {

    private long surveyId;
    private String question;
    private List<SurveyChoiceDto> choices;
    private int maxChoicesNumber;
    private long eventId;
    private Set<SurveyResponseDto> responses;

    private Map<Long, BigDecimal> choicePercent;

    @Override
    public String toString() {
        return "SurveyDto{" +
                "surveyId=" + surveyId +
                ", question='" + question + '\'' +
                ", choices=" + choices +
                ", maxChoicesNumber=" + maxChoicesNumber +
                ", eventId=" + eventId +
                ", responses=" + responses +
                '}';
    }
}
