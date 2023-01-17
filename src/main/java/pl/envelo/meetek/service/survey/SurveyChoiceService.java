package pl.envelo.meetek.service.survey;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.survey.SurveyChoiceRepo;

@AllArgsConstructor
@Service
public class SurveyChoiceService {

    private final SurveyChoiceRepo surveyChoiceRepo;
}
