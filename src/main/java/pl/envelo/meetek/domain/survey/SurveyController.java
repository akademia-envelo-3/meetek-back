package pl.envelo.meetek.domain.survey;

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
import pl.envelo.meetek.domain.survey.model.SurveyDto;
import pl.envelo.meetek.domain.survey.model.SurveyResponse;
import pl.envelo.meetek.domain.survey.model.SurveyResponseCreateDto;
import pl.envelo.meetek.domain.user.StandardUserService;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.net.URI;

@AllArgsConstructor
@RestController
@Tag(name = "Survey")
@RequestMapping("/surveys")
public class SurveyController {

    private SurveyService surveyService;
    private StandardUserService standardUserService;

    //testowo - do usunięcia
    @GetMapping
    public SurveyDto getSurvey(@RequestParam Long id) {
        return surveyService.getSurvey(id);
    }

    @PostMapping("/{surveyId}")
    @Operation(summary = "Create a new response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Response created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong paramets", content = @Content)})
    public ResponseEntity<Void> addResponse(@PathVariable long surveyId, @RequestParam long userId, @RequestBody SurveyResponseCreateDto surveyResponseCreateDto) {
        StandardUser standardUser = standardUserService.getStandardUserById(userId);
        SurveyResponse surveyResponse = surveyService.addResponse(surveyId, standardUser, surveyResponseCreateDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(surveyResponse.getResponseId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

}
