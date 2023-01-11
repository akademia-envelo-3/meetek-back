package pl.envelo.meetek.service.survey;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.survey.SurveyResponseRepo;

@AllArgsConstructor
@Service
public class SurveyResponseService {

    private final SurveyResponseRepo surveyResponseRepo;
}
