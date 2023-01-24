package pl.envelo.meetek.service.survey;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.survey.SurveyResponse;
import pl.envelo.meetek.repository.survey.SurveyResponseRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class SurveyResponseService {

    private final SurveyResponseRepo surveyResponseRepo;

    @Transactional
    public void createSurveyResponse(SurveyResponse surveyResponse){
        surveyResponseRepo.save(surveyResponse);
    }

    @Transactional(readOnly = true)
    public Optional<SurveyResponse> getSurveyResponse(long surveyResponseId){
        return surveyResponseRepo.findById(surveyResponseId);
    }

    @Transactional
    public void deleteSurveyResponse(long surveyResponseId){
        surveyResponseRepo.deleteById(surveyResponseId);
    }
}
