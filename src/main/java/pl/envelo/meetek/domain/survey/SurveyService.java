package pl.envelo.meetek.domain.survey;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.survey.model.Survey;
import pl.envelo.meetek.domain.survey.model.SurveyChoice;
import pl.envelo.meetek.domain.survey.model.SurveyResponse;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@AllArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepo surveyRepo;
    private final SurveyChoiceService surveyChoiceService;

    public final SurveyResponseService surveyResponseService;

    @Transactional
    public Survey createSurvey(Survey survey) {
        for (SurveyChoice surveyChoice : survey.getChoices()) {
            if(surveyChoiceService.getSurveyChoiceByDescription(surveyChoice.getDescription()).isEmpty())
                surveyChoiceService.createSurveyChoice(surveyChoice);
        }
        return surveyRepo.save(survey);
    }

    @Transactional(readOnly = true)
    public Optional<Survey> getSurvey(long surveyId) {
        Optional<Survey> surveyOptional = surveyRepo.findById(surveyId);
        surveyOptional.ifPresent(this::setSurveyFields);

        return surveyOptional;
    }

    @Transactional
    public Optional<SurveyResponse> addResponse(long surveyId, StandardUser standardUser, SurveyResponse surveyResponseBody) {
        Optional<Survey> surveyOptional = surveyRepo.findById(surveyId);

        if (surveyOptional.isPresent()) {
            surveyResponseBody.setUser(standardUser);
            SurveyResponse surveyResponse = surveyResponseService.createSurveyResponse(surveyResponseBody);
            surveyOptional.get().getResponses().add(surveyResponse);
            return Optional.of(surveyResponse);
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
