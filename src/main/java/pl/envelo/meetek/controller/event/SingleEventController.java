package pl.envelo.meetek.controller.event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import pl.envelo.meetek.dto.event.SingleEventLongDto;
import pl.envelo.meetek.dto.event.SingleEventShortDto;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.service.DtoMapperService;
import pl.envelo.meetek.service.event.SingleEventService;

import java.net.URI;
import java.util.Optional;

@AllArgsConstructor
@RestController
@Tag(name = "Event")
@RequestMapping("/${app.prefix}/${app.version}/events")
public class SingleEventController {

    private SingleEventService singleEventService;
    private DtoMapperService dtoMapperService;

    @GetMapping("/{eventId}")
    @Operation(summary = "Get an event by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the event",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SingleEventLongDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)})
    public ResponseEntity getEvent(@PathVariable long eventId, @Parameter(description = "for all information about event use: details, for basic info leave blank") @RequestParam(required = false) String fields) {
        Optional<SingleEvent> eventOptional = singleEventService.getSingleEventById(eventId);
        if (eventOptional.isPresent()) {
            SingleEvent event = eventOptional.get();
            Object dto;
            if (fields != null) {
                switch (fields) {
                    case "details" -> dto = dtoMapperService.mapToSingleEventLongDto(event);
                    default -> dto = dtoMapperService.mapToSingleEventShortDto(event);
                }
            } else {
                dto = dtoMapperService.mapToSingleEventShortDto(event);
            }
            return new ResponseEntity(dto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a new event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, parameters are wrong", content = @Content)})
    public ResponseEntity<Void> saveNewEvent(@RequestBody SingleEventShortDto eventDto) {
        SingleEvent entity = singleEventService.saveNewSingleEvent(dtoMapperService.mapToSingleEvent(eventDto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getEventId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
