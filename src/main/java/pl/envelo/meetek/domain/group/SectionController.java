package pl.envelo.meetek.domain.group;

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
import pl.envelo.meetek.domain.event.model.SingleEventShortDto;
import pl.envelo.meetek.domain.group.model.SectionCreateDto;
import pl.envelo.meetek.domain.group.model.SectionLongDto;
import pl.envelo.meetek.domain.group.model.SectionShortDto;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.domain.user.StandardUserService;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Section")
@RequestMapping("/${app.prefix}/${app.version}/sections")
public class SectionController {

    private final SectionService sectionService;
    private final StandardUserService standardUserService;

    @GetMapping("/{sectionId}")
    @Operation(summary = "Get section by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Section found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SectionLongDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid sectionId format", content = @Content),
            @ApiResponse(responseCode = "404", description = "Section not found", content = @Content)})
    public ResponseEntity<SectionLongDto> getSection(@PathVariable long sectionId) {
        SectionLongDto section = sectionService.getSectionById(sectionId);
        return new ResponseEntity<>(section, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create new section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Section created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong parameters", content = @Content)})
    public ResponseEntity<Void> createSection(@RequestParam long userId, @RequestBody SectionCreateDto sectionCreateDto) {
        StandardUser user = standardUserService.getStandardUserById(userId);
        SectionShortDto section = sectionService.createSection(user, sectionCreateDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(section.getGroupId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{sectionId}")
    @Operation(summary = "Edit section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Section edited",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SectionCreateDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid sectionId", content = @Content),
            @ApiResponse(responseCode = "404", description = "Section not found", content = @Content)})
    public ResponseEntity<Void> editSection(@PathVariable long sectionId, @RequestParam long userId, @RequestBody SectionLongDto sectionDto) {
        StandardUser validatedUser = standardUserService.getStandardUserById(userId);
        sectionService.editSection(sectionId, sectionDto, validatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{sectionId}")
    @Operation(summary = "Deactivate section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Section deactivated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SectionCreateDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid sectionId", content = @Content),
            @ApiResponse(responseCode = "404", description = "Section not found", content = @Content)})
    public ResponseEntity<Void> deactivateSection(@PathVariable long sectionId, @RequestParam long userId) {
        StandardUser validatedUser = standardUserService.getStandardUserById(userId);
        sectionService.deactivateSection(sectionId, validatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "Get all active sections")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SectionShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No section found", content = @Content)})
    public ResponseEntity<List<SectionShortDto>> getActiveSections() {
        List<SectionShortDto> sections = sectionService.getAllActiveSections();
        HttpStatus status = sections.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(sections, status);
    }

    @GetMapping("/joined")
    @Operation(summary = "Get all joined sections")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SectionShortDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong userId", content = @Content),
            @ApiResponse(responseCode = "204", description = "No section found", content = @Content)})
    public ResponseEntity<List<SectionShortDto>> getAllJoinedSections(@RequestParam long userId) {
        StandardUser validatedUser = standardUserService.getStandardUserById(userId);
        List<SectionShortDto> sections = sectionService.getAllJoinedSections(validatedUser.getParticipantId());
        HttpStatus status = sections.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(sections, status);
    }

    @GetMapping("owned/{userId}")
    @Operation(summary = "Get all owned sections")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SectionShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No section found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid userId", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    public ResponseEntity<List<SectionShortDto>> getOwnedSections(@PathVariable long userId) {
        StandardUser validatedUser = standardUserService.getStandardUserById(userId);
        List<SectionShortDto> sections = sectionService.getAllOwnedSectionsByUserId(validatedUser.getParticipantId());
        HttpStatus status = sections.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(sections, status);
    }

    @GetMapping("/{sectionId}/events")
    @Operation(summary = "Get events of this section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SectionLongDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid sectionId format", content = @Content),
            @ApiResponse(responseCode = "404", description = "Section not found", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getSectionEvents(@PathVariable long sectionId, @RequestParam String time) {
        List<SingleEventShortDto> sections = sectionService.getAllEventsOfSection(sectionId, time);
        HttpStatus status = sections.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(sections, status);
    }

}
