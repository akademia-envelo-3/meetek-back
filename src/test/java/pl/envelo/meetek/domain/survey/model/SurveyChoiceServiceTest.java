package pl.envelo.meetek.domain.survey.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.survey.SurveyChoiceRepo;
import pl.envelo.meetek.domain.survey.SurveyChoiceService;
import pl.envelo.meetek.domain.survey.SurveyValidator;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.DtoMapperService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyChoiceServiceTest {

    @Mock
    private SurveyChoiceRepo surveyChoiceRepo;

    @Mock
    private SurveyValidator surveyValidator;

    @Mock
    private DtoMapperService mapperService;

    @InjectMocks
    private SurveyChoiceService surveyChoiceService;

    //Tests to create SurveyChoice: Success and Failure
}

