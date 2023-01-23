package pl.envelo.meetek.service.survey;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.survey.Survey;
import pl.envelo.meetek.repository.survey.SurveyRepo;

import java.util.*;

@AllArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepo surveyRepo;

    public Survey createSurvey(Survey survey){
        return surveyRepo.save(survey);
    }
    public Optional<Survey> getSurvey(long surveyId){
    return surveyRepo.findById(surveyId);
    }
    public void deleteSurvey(long surveyId){
        surveyRepo.deleteById(surveyId);
    }
}
