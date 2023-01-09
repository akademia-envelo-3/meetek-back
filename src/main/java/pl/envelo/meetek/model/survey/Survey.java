package pl.envelo.meetek.model.survey;

import pl.envelo.meetek.model.event.Event;

import java.util.Set;

public class Survey {

    private Long surveyId;
    private String question;
    private Set<SurveyChoice> choices;
    private int maxChoicesNumber;
    private Event event;
    private Set<SurveyResponse> responses;

}
