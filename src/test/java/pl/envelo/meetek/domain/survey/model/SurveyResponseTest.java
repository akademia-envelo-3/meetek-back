package pl.envelo.meetek.domain.survey.model;

import org.junit.jupiter.api.Test;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

public class SurveyResponseTest {


    @Test
    void testEqualsAndHashCode() {
        SurveyChoice choice1 = mock(SurveyChoice.class);
        SurveyChoice choice2 = mock(SurveyChoice.class);
        StandardUser user = mock(StandardUser.class);
        Set<SurveyChoice> answers = Set.of(choice1, choice2);

        SurveyResponse response1 = new SurveyResponse();
        response1.setResponseId(1L);
        response1.setUser(user);
        response1.setAnswers(answers);

        SurveyResponse response2 = new SurveyResponse();
        response2.setResponseId(1L);
        response2.setUser(user);
        response2.setAnswers(answers);

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());

        response2.setUser(null);

        assertNotEquals(response1, response2);
        assertNotEquals(response1.hashCode(), response2.hashCode());
    }
}