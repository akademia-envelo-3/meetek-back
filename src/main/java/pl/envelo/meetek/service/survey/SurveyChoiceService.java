package pl.envelo.meetek.service.survey;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.survey.SurveyChoice;
import pl.envelo.meetek.repository.survey.SurveyChoiceRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class SurveyChoiceService {

    private final SurveyChoiceRepo surveyChoiceRepo;

    @Transactional
    public void createSurveyChoice(SurveyChoice surveyChoice){
        surveyChoiceRepo.save(surveyChoice);
    }

    @Transactional(readOnly = true)
    public Optional<SurveyChoice> getSurveyChoice(long surveyChoiceId){
        return surveyChoiceRepo.findById(surveyChoiceId);
    }

    @Transactional
    public void deleteSurveyChoice(long surveyChoiceId){
        surveyChoiceRepo.deleteById(surveyChoiceId);
    }

}
