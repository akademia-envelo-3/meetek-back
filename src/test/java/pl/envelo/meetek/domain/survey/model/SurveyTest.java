package pl.envelo.meetek.domain.survey.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SurveyTest {

    @Test
    public void testToString() {
        Survey survey = new Survey();
        survey.setSurveyId(1L);
        survey.setQuestion("Question");
        survey.setMaxChoicesNumber(5);

        String expected = "Survey{surveyId=1, question='Question', choices=null, maxChoicesNumber=5, event=null, responses=null}";
        assertEquals(expected, survey.toString());
    }

    @Test
    public void testHashCode() {
        Survey survey1 = new Survey();
        survey1.setSurveyId(1L);
        survey1.setQuestion("Question");
        survey1.setMaxChoicesNumber(5);
        Survey survey2 = new Survey();
        survey2.setSurveyId(1L);
        survey2.setQuestion("Question");
        survey2.setMaxChoicesNumber(5);
        Survey survey3 = new Survey();
        survey3.setSurveyId(2L);
        survey3.setQuestion("Question");
        survey3.setMaxChoicesNumber(5);

        assertEquals(survey1.hashCode(), survey2.hashCode());
        assertNotEquals(survey1.hashCode(), survey3.hashCode());
    }

    @Test
    public void testEquals() {
        Survey survey1 = new Survey();
        survey1.setSurveyId(1L);
        survey1.setQuestion("Question");
        survey1.setMaxChoicesNumber(5);
        Survey survey2 = new Survey();
        survey2.setSurveyId(1L);
        survey2.setQuestion("Question");
        survey2.setMaxChoicesNumber(5);
        Survey survey3 = new Survey();
        survey3.setSurveyId(2L);
        survey3.setQuestion("Question");
        survey3.setMaxChoicesNumber(5);

        assertEquals(survey1, survey2);
        assertNotEquals(survey1, survey3);
    }
}