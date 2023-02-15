package pl.envelo.meetek.domain.survey;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.survey.model.SurveyResponse;

import java.util.Optional;

@AllArgsConstructor
@Service
public class SurveyResponseService {

    private final SurveyResponseRepo surveyResponseRepo;

    @Transactional
    public SurveyResponse createSurveyResponse(SurveyResponse surveyResponse) {
        return surveyResponseRepo.save(surveyResponse);
    }

    @Transactional(readOnly = true)
    public Optional<SurveyResponse> getSurveyResponse(long surveyResponseId) {
        return surveyResponseRepo.findById(surveyResponseId);
    }

    @Transactional
    public void deleteSurveyResponse(long surveyResponseId) {
        surveyResponseRepo.deleteById(surveyResponseId);
    }
}
