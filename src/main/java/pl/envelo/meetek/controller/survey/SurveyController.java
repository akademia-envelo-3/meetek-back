package pl.envelo.meetek.controller.survey;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.envelo.meetek.dto.survey.SurveyDto;
import pl.envelo.meetek.dto.survey.SurveyResponseDto;
import pl.envelo.meetek.model.survey.Survey;
import pl.envelo.meetek.model.survey.SurveyResponse;
import pl.envelo.meetek.service.DtoMapperService;
import pl.envelo.meetek.service.survey.SurveyResponseService;
import pl.envelo.meetek.service.survey.SurveyService;

import java.net.URI;
import java.util.Optional;

@AllArgsConstructor
@RestController
@Tag(name = "Survey")
@RequestMapping("/surveys")
public class SurveyController {

    private SurveyService surveyService;

    private SurveyResponseService surveyResponseService;
    private DtoMapperService dtoMapperService;

    //testowo - do usunięcia
    @GetMapping
    public SurveyDto getSurvey(@RequestParam Long id) {
        Optional<Survey> surveyOptional = surveyService.getSurvey(id);
        return surveyOptional.map(dtoMapperService::mapToSurveyDto).orElse(null);
    }

    @PostMapping("/{surveyId}")
    @Operation(summary = "Create a new response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Response created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong paramets", content = @Content)})
    public ResponseEntity<Void> addResponse(@PathVariable long surveyId, @RequestBody SurveyResponseDto surveyResponseDto) {
        Optional<SurveyResponse> surveyResponse = surveyService.addResponse(surveyId, dtoMapperService.mapToSurveyResponse(surveyResponseDto));
        if (surveyResponse.isPresent()) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(surveyResponse.get().getResponseId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.badRequest().build();
    }

}
