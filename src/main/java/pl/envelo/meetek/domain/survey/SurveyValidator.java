package pl.envelo.meetek.domain.survey;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.survey.model.Survey;
import pl.envelo.meetek.domain.survey.model.SurveyChoice;
import pl.envelo.meetek.domain.survey.model.SurveyResponse;
import pl.envelo.meetek.exceptions.ArgumentNotValidException;
import pl.envelo.meetek.exceptions.DuplicateException;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class SurveyValidator extends ValidatorService<Survey> {

    private final SurveyRepo surveyRepo;
    private final SurveyChoiceRepo surveyChoiceRepo;

    public SurveyValidator(Validator validator, SurveyRepo surveyRepo, SurveyChoiceRepo surveyChoiceRepo) {
        super(validator);
        this.surveyRepo = surveyRepo;
        this.surveyChoiceRepo = surveyChoiceRepo;
    }

    public void validateChoiceInput(SurveyChoice t) {
        Set<ConstraintViolation<SurveyChoice>> violations = validator.validate(t);
        if (!violations.isEmpty()) {
            Map<String, String> messages = new HashMap<>();
            for (ConstraintViolation<SurveyChoice> violation : violations) {
                messages.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            throw new ArgumentNotValidException("Not valid data provided", messages);
        }
    }

    public void validateResponseInput(SurveyResponse t) {
        Set<ConstraintViolation<SurveyResponse>> violations = validator.validate(t);
        if (!violations.isEmpty()) {
            Map<String, String> messages = new HashMap<>();
            for (ConstraintViolation<SurveyResponse> violation : violations) {
                messages.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            throw new ArgumentNotValidException("Not valid data provided", messages);
        }
    }

    @Override
    public Survey validateExists(long id) {
        return surveyRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Survey with id " + id + " not found"));
    }

    public SurveyChoice validateChoiceExists(long id) {
        return surveyChoiceRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Survey choice with id " + id + " not found"));
    }

    public void validateSurveyNotDuplicate(String question) {
        surveyRepo.findByQuestion(question).ifPresent(survey -> {
            throw new DuplicateException("Survey with name " + question + " already exists.");
        });
    }

    public void validateChoiceNotDuplicate(String choice) {
        surveyChoiceRepo.findByDescription(choice).ifPresent(survey -> {
            throw new DuplicateException("Survey with name " + choice + " already exists.");
        });
    }

}
