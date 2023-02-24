package pl.envelo.meetek.domain.survey;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.survey.model.*;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.utils.DtoMapperService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@AllArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepo surveyRepo;
    private final SurveyChoiceService surveyChoiceService;
    public final SurveyResponseService surveyResponseService;
    private final SurveyValidator surveyValidator;
    private final DtoMapperService dtoMapperService;

    @Transactional
    public Survey createSurvey(SurveyDto surveyDto) {
        Survey survey = dtoMapperService.mapToSurvey(surveyDto);
        for (SurveyChoice surveyChoice : survey.getChoices()) {
            if (surveyChoiceService.getSurveyChoiceByDescription(surveyChoice.getDescription()).isEmpty()) {
                surveyValidator.validateInputComponent(surveyChoice);
                surveyChoiceService.createSurveyChoice(surveyChoice);
            }
        }
        surveyValidator.validateInput(survey);
        return surveyRepo.save(survey);
    }

    @Transactional(readOnly = true)
    public SurveyDto getSurvey(long surveyId) {
        return dtoMapperService.mapToSurveyDto(surveyValidator.validateExists(surveyId));
    }

    @Transactional
    public SurveyResponse addResponse(long surveyId, StandardUser standardUser, SurveyResponseCreateDto surveyResponseBody) {
        SurveyResponse surveyResponse = dtoMapperService.mapToSurveyResponseCreate(surveyResponseBody);
        Survey survey = surveyValidator.validateExists(surveyId);
        surveyResponse.setUser(standardUser);
        surveyValidator.validateInputComponent(surveyResponseBody);
        surveyValidator.validateUserResponsed(survey, standardUser);
        surveyValidator.validateUserRightResponsesQty(survey, surveyResponse);
        surveyResponseService.createSurveyResponse(surveyResponse);
        survey.getResponses().add(surveyResponse);
        return surveyResponse;

    }

    @Transactional
    public void deleteSurvey(long surveyId) {
        surveyValidator.validateExists(surveyId);
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
