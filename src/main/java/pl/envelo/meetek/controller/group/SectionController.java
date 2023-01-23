package pl.envelo.meetek.controller.group;

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
import pl.envelo.meetek.dto.group.SectionLongDto;
import pl.envelo.meetek.dto.group.SectionShortDto;
import pl.envelo.meetek.model.group.Section;
import pl.envelo.meetek.service.DtoMapperService;
import pl.envelo.meetek.service.group.SectionService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@Tag(name = "Section")
@RequestMapping("/${app.prefix}/${app.version}/sections")
public class SectionController {

    private final SectionService sectionService;
    private final DtoMapperService dtoMapperService;

    @GetMapping()
    @Operation(summary = "Get all active sections")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SectionShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No section found", content = @Content)})
    public ResponseEntity<List<SectionShortDto>> getActiveSections() {
        List<Section> sections = sectionService.getAllActiveSections();
        List<SectionShortDto> dtoSections = sections.stream()
                .map(dtoMapperService::mapToSectionShortDto)
                .toList();
        HttpStatus status = dtoSections.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(dtoSections, status);
    }

    @GetMapping("/{sectionId}")
    @Operation(summary = "Get a section by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "section found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SectionLongDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid sectionId format", content = @Content),
            @ApiResponse(responseCode = "404", description = "Section not found", content = @Content)})
    public ResponseEntity<SectionLongDto> getSection(@PathVariable long sectionId) {
        Optional<Section> section = sectionService.getSectionById(sectionId);
        return section.map(dtoMapperService::mapToSectionLongDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PutMapping("/{sectionId}")
    @Operation(summary = "Edit section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "section edited",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SectionLongDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid sectionId", content = @Content),
            @ApiResponse(responseCode = "404", description = "Section not found", content = @Content)})
    public ResponseEntity<Void> editSection(@PathVariable long sectionId, @RequestBody SectionLongDto sectionDto) {
        if (sectionDto.getGroupId() != sectionId) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Section> section = sectionService.getSectionById(sectionId);
        if (section.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            sectionService.editSection(section.get(), sectionDto);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("owned/{userId}")
    @Operation(summary = "Get all owned sections")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SectionShortDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Invalid userId found", content = @Content)})
    public ResponseEntity<List<SectionShortDto>> getOwnedSections(@PathVariable long userId) {
        List<Section> ownedSections = sectionService.getOwnedSectionsByUserId(userId);
        List<SectionShortDto> dtoSections = ownedSections.stream()
                .map(dtoMapperService::mapToSectionShortDto)
                .toList();
        HttpStatus status = dtoSections.isEmpty() ? HttpStatus.BAD_REQUEST: HttpStatus.OK;
        return new ResponseEntity<>(dtoSections, status);
    }
}
