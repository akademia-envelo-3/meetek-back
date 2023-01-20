package pl.envelo.meetek.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.envelo.meetek.dto.HashtagDto;
import pl.envelo.meetek.model.Hashtag;
import pl.envelo.meetek.service.DtoMapperService;
import pl.envelo.meetek.service.HashtagService;

import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Hashtag")
@RequestMapping("/${app.prefix}/${app.version}/hashtags")
public class HashtagController {

    private final HashtagService hashtagService;
    private final DtoMapperService dtoMapperService;

    @GetMapping()
    @Operation(summary = "Get all active hashtags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = HashtagDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No hashtag found", content = @Content)})
    public ResponseEntity<List<HashtagDto>> getActiveHashtags() {
        List<Hashtag> hashtags = hashtagService.getAllActiveHashtags();
        List<HashtagDto> dtoHashtags = hashtags.stream()
                .map(dtoMapperService::mapToHashtagDto)
                .toList();
        HttpStatus status = dtoHashtags.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(dtoHashtags, status);
    }
}
