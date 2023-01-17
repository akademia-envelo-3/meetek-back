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

import java.util.List;
import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @DeleteMapping("/{eventId}")
    @Operation(summary = "Delete event by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event removed successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)})
    public ResponseEntity<Void> deleteEvent(@PathVariable long eventId) {

        if (singleEventService.getSingleEventById(eventId).isPresent()) {
            singleEventService.deleteById(eventId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/past")
    @Operation(summary = "Get all public past events where the user with given ID didn't confirm his participation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SingleEventShortDto.class))}),
            @ApiResponse(responseCode = "404", description = "No event found", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getAllPublicPastNotAcceptedEvents(@RequestParam long userId) {
        List<SingleEvent> events = singleEventService.getAllPublicPastNotAcceptedEvents(userId);
        List<SingleEventShortDto> dtoEvents = events.stream()
                .map(dtoMapperService::mapToSingleEventShortDto)
                .toList();
        if (dtoEvents.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(dtoEvents, HttpStatus.OK);
    }

    @GetMapping("/future")
    @Operation(summary = "Get public future events not accepted by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SingleEventShortDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Events not found", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getAllPublicFutureNotAcceptedEvents(
            @RequestParam long userId,
            @Parameter(description = "To get events for few days set number of days")
            @RequestParam(required = false) Integer days) {

        List<SingleEvent> events;
        List<SingleEventShortDto> eventShortDtos;

        if (days == null) {
            events = singleEventService.getAllPublicFutureNotAcceptedEvents(userId);
        } else {
            if (days < 1) {
                days = 1;
            }
            events = singleEventService.getAllPublicFutureNotAcceptedEventsForFewNearestDays(userId, days);
        }

        eventShortDtos = events.stream().
                map(dtoMapperService::mapToSingleEventShortDto).
                collect(Collectors.toList());

        if (!events.isEmpty()) {
            return new ResponseEntity(eventShortDtos, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/future/accepted")
    @Operation(summary = "Get all future events accepted by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SingleEventShortDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Events not found", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getAllFutureAcceptedEvents(
            @RequestParam long userId,
            @Parameter(description = "To get events for few days set number of days")
            @RequestParam(required = false) Integer days) {

        List<SingleEvent> events;
        List<SingleEventShortDto> eventShortDtos;

        if (days == null) {
            events = singleEventService.getAllFutureAcceptedEvents(userId);
        } else {
            if (days < 1) {
                days = 1;
            }
            events = singleEventService.getAllFutureAcceptedEventsForFewNearestDays(userId, days);
        }

        eventShortDtos = events.stream().
                map(dtoMapperService::mapToSingleEventShortDto).
                collect(Collectors.toList());

        if (!events.isEmpty()) {
            return new ResponseEntity(eventShortDtos, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/past/accepted")
    @Operation(summary = "Get all past events where the user with given ID confirmed his participation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SingleEventShortDto.class))}),
            @ApiResponse(responseCode = "404", description = "No event found", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getAllPastAcceptedEvents(@RequestParam long userId) {
        List<SingleEvent> events = singleEventService.getAllPastAcceptedEvents(userId);
        List<SingleEventShortDto> dtoEvents = events.stream()
                .map(dtoMapperService::mapToSingleEventShortDto)
                .toList();
        if (dtoEvents.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(dtoEvents, HttpStatus.OK);
    }

    @GetMapping("/future/owned")
    @Operation(summary = "Get all future events owned by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SingleEventShortDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Events not found", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getFutureOwnedByUser(
            @RequestParam long userId) {

        List<SingleEvent> futureOwnedEvents;
        List<SingleEventShortDto> futureOwnedEventShortDtos;

        futureOwnedEvents = singleEventService.findAllPublicFutureOwnedByDateTimeFromAfterOrderByDateTimeFromAsc(userId);

        futureOwnedEventShortDtos = futureOwnedEvents.stream().
                map(singleEvent -> dtoMapperService.
                        mapToSingleEventShortDto(singleEvent)).
                collect(Collectors.toList());

        if (!futureOwnedEvents.isEmpty()) {
            return new ResponseEntity(futureOwnedEventShortDtos, HttpStatus.OK);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
