package pl.envelo.meetek.model.survey;

import pl.envelo.meetek.model.user.AppUser;

import java.util.Set;

public class SurveyResponse {

    private Long responseId;
    private Set<SurveyChoice> answers;
    private AppUser user;

}
