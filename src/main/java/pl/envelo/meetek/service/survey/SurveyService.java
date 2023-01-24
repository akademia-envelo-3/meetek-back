package pl.envelo.meetek.service.survey;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.survey.Survey;
import pl.envelo.meetek.repository.survey.SurveyRepo;

import java.util.*;

@AllArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepo surveyRepo;

    @Transactional
    public Survey createSurvey(Survey survey){
        return surveyRepo.save(survey);
    }

    @Transactional(readOnly = true)
    public Optional<Survey> getSurvey(long surveyId){
    return surveyRepo.findById(surveyId);
    }

    @Transactional
    public void deleteSurvey(long surveyId){
        surveyRepo.deleteById(surveyId);
    }

}
