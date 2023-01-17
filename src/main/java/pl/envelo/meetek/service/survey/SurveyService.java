package pl.envelo.meetek.service.survey;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.survey.SurveyRepo;

@AllArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepo surveyRepo;
}
