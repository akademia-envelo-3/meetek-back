package pl.envelo.meetek.controller.survey;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.envelo.meetek.dto.survey.SurveyDto;
import pl.envelo.meetek.model.survey.Survey;
import pl.envelo.meetek.service.DtoMapperService;
import pl.envelo.meetek.service.survey.SurveyService;

import java.util.Optional;

@AllArgsConstructor
@RestController
@Tag(name = "Survey")
public class SurveyController {

    private SurveyService surveyService;
    private DtoMapperService dtoMapperService;

    //testowo - do usunięcia
    @GetMapping("/surveys")
    public SurveyDto getSurvey(@RequestParam Long id){
        Optional<Survey> surveyOptional = surveyService.getSurvey(id);
        if (surveyOptional.isPresent()) {
            Survey survey = surveyOptional.get();
            return dtoMapperService.mapToSurveyDto(survey);
        }
        return null;
    }
}
