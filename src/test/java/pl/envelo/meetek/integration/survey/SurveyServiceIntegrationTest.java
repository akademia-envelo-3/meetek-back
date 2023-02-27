package pl.envelo.meetek.integration.survey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.event.SingleEventRepo;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.domain.survey.SurveyChoiceRepo;
import pl.envelo.meetek.domain.survey.SurveyRepo;
import pl.envelo.meetek.domain.survey.SurveyService;
import pl.envelo.meetek.domain.survey.model.*;
import pl.envelo.meetek.domain.user.StandardUserRepo;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class SurveyServiceIntegrationTest {

    @Autowired
    SurveyService surveyService;

    @Autowired
    SurveyRepo surveyRepo;

    @Autowired
    SingleEventRepo eventRepo;

    @Autowired
    SurveyChoiceRepo choiceRepo;

    @Autowired
    StandardUserRepo userRepo;

    @Autowired
    DtoMapperService mapperService;

    @Test
    void addResponse_whenDataValid_Integration(){
        // given
        StandardUser user = userRepo.save(new StandardUser());
        SurveyChoice choice = choiceRepo.save(new SurveyChoice(0L, "yes"));
        List<SurveyChoice> choices = Arrays.asList(choice);
        Set<SurveyChoiceDto> answers = new HashSet<>();
        answers.add(mapperService.mapToSurveyChoiceDto(choice));

        String question = "WhatWhatWhatWhat";
        SingleEvent event = eventRepo.save(new SingleEvent());
        Survey survey = surveyRepo.save(new Survey(0L, question, choices, 1, event, new HashSet<>(), new HashMap<>()));
        SurveyResponseCreateDto responseCreateDto = new SurveyResponseCreateDto(answers);
        // when
        SurveyResponse response = surveyService.addResponse(survey.getSurveyId(), user, responseCreateDto);
        // then
        assertThat(response.getAnswers().stream().findFirst().get().getChoiceId()).isEqualTo(choice.getChoiceId());
        assertThat(response.getUser().getParticipantId()).isEqualTo(user.getParticipantId());
        assertThat(surveyService.getSurvey(survey.getSurveyId()).getResponses().isEmpty()).isFalse();
    }

}
