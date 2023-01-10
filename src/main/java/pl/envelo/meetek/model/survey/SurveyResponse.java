package pl.envelo.meetek.model.survey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.user.AppUser;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SurveyResponse {

    private Long responseId;
    private Set<SurveyChoice> answers;
    private AppUser user;

}
