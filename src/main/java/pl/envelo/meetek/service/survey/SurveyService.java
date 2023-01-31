package pl.envelo.meetek.service.survey;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.survey.Survey;
import pl.envelo.meetek.model.survey.SurveyChoice;
import pl.envelo.meetek.model.survey.SurveyResponse;
import pl.envelo.meetek.repository.survey.SurveyRepo;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@AllArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepo surveyRepo;

    public final SurveyResponseService surveyResponseService;

    @Transactional
    public Survey createSurvey(Survey survey) {
        return surveyRepo.save(survey);
    }

    @Transactional(readOnly = true)
    public Optional<Survey> getSurvey(long surveyId) {
        Optional<Survey> surveyOptional = surveyRepo.findById(surveyId);
        surveyOptional.ifPresent(this::setSurveyFields);

        return surveyOptional;
    }

    @Transactional
    public Optional<SurveyResponse> addResponse(long surveyId, SurveyResponse surveyResponse) {
        Optional<Survey> surveyOptional = surveyRepo.findById(surveyId);
        if (surveyOptional.isPresent()) {
            SurveyResponse surveyResponse1 = surveyResponseService.createSurveyResponse(surveyResponse);
            surveyOptional.get().getResponses().add(surveyResponse1);
            return Optional.of(surveyResponse1);
        }
        return Optional.empty();
    }

    @Transactional
    public void deleteSurvey(long surveyId) {
        surveyRepo.deleteById(surveyId);
    }

    @Transactional
    public void setSurveyFields(Survey survey) {
        if (!survey.getResponses().isEmpty()) {
            survey.setChoicePercent(calculatePercentage(createResponseCount(survey)));
        }
    }

    private Map<Long, Integer> createResponseCount(Survey survey) {

        Map<Long, Integer> responseCount = new HashMap<>();
        for (SurveyChoice surveyChoice : survey.getChoices()) {
            responseCount.put(surveyChoice.getChoiceId(), 0);
        }
        for (SurveyResponse surveyResponse : survey.getResponses()) {
            for (SurveyChoice answer : surveyResponse.getAnswers()) {
                responseCount.put(answer.getChoiceId(), responseCount.get(answer.getChoiceId()) + 1);
            }
        }
        return responseCount;
    }

    private Map<Long, BigDecimal> calculatePercentage(Map<Long, Integer> responseCount) {

        double value = 0;
        Map<Long, BigDecimal> choicePercent = new HashMap<>();
        for (Long key : responseCount.keySet()) {
            value = value + responseCount.get(key);
        }
        for (Long key : responseCount.keySet()) {
            choicePercent.put(key, BigDecimal.valueOf(responseCount.get(key) / value * 100).setScale(2, RoundingMode.HALF_DOWN));
        }
        return choicePercent;
    }
}
