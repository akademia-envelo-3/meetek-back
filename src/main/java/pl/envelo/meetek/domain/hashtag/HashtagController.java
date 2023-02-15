package pl.envelo.meetek.domain.hashtag;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Hashtag")
@RequestMapping("/${app.prefix}/${app.version}/hashtags")
public class HashtagController {

    private final HashtagService hashtagService;

    @GetMapping("/{hashtagId}")
    @Operation(summary = "Get a hashtag by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hashtag found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HashtagDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invaild hashtagId format", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hashtag not found", content = @Content)})
    public ResponseEntity<HashtagDto> getHashtag(@PathVariable long hashtagId) {
        return new ResponseEntity<>(hashtagService.getHashtagById(hashtagId), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new hashtag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hashtag created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong paramets", content = @Content)})
    public ResponseEntity<Void> saveNewHashtag(@RequestBody HashtagCreateDto hashtagCreateDto) {
        Hashtag hashtag = hashtagService.createHashtag(hashtagCreateDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(hashtag.getHashtagId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{hashtagId}")
    @Operation(summary = "Edit hashtag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hashtag updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong parameters", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hashtag not found", content = @Content)})
    public ResponseEntity<Void> editHashtag(@PathVariable long hashtagId, @RequestBody HashtagCreateDto hashtagCreateDto) {
        hashtagService.editHashtag(hashtagId, hashtagCreateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "Get all active hashtags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = HashtagDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No hashtag found", content = @Content)})
    public ResponseEntity<List<HashtagDto>> getActiveHashtags() {
        List<HashtagDto> hashtags = hashtagService.getAllActiveHashtags();
        return hashtags.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(hashtags, HttpStatus.OK);
    }
}
