package pl.envelo.meetek.domain.survey.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class SurveyChoiceTests {

    private SurveyChoice surveyChoice1, surveyChoice2;

    @BeforeEach
    void setUp() {
        surveyChoice1 = new SurveyChoice(1L, "Option A");
        surveyChoice2 = new SurveyChoice(1L, "Option A");
    }

    @Test
    void testToString() {
        String expected = "SurveyChoice{choiceId=1, description='Option A'}";
        String actual = surveyChoice1.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testHashCode() {
        assertEquals(surveyChoice1.hashCode(), surveyChoice2.hashCode());
    }

    @Test
    void testEquals() {
        assertTrue(surveyChoice1.equals(surveyChoice2));
    }
}