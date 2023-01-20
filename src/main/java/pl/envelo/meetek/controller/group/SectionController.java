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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
