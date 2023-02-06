package pl.envelo.meetek.controller.event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import pl.envelo.meetek.dto.comment.EventCommentDto;
import pl.envelo.meetek.dto.event.SingleEventCreateDto;
import pl.envelo.meetek.dto.event.SingleEventLongDto;
import pl.envelo.meetek.dto.event.SingleEventShortDto;
import pl.envelo.meetek.model.comment.EventComment;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.service.DtoMapperService;
import pl.envelo.meetek.service.comment.EventCommentService;
import pl.envelo.meetek.service.event.SingleEventService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Tag(name = "Event")
@RequestMapping("/${app.prefix}/${app.version}/events")
public class SingleEventController {

    private final SingleEventService singleEventService;
    private final EventCommentService eventCommentService;
    private final DtoMapperService dtoMapperService;


    @PostMapping
    @Operation(summary = "Create a new event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, parameters are wrong", content = @Content)})
    public ResponseEntity<Void> saveNewEvent(@RequestBody SingleEventCreateDto eventDto) {
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
            @ApiResponse(responseCode = "400", description = "Bad request, wrong eventId", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)})
    public ResponseEntity<Void> deleteEvent(@PathVariable long eventId) {

        if (singleEventService.getSingleEventById(eventId).isPresent()) {
            singleEventService.deleteById(eventId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{eventId}")
    @Operation(summary = "Get an event by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the event",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SingleEventLongDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)})
    public ResponseEntity<SingleEventLongDto> getEvent(@PathVariable long eventId) {
        Optional<SingleEvent> eventOptional = singleEventService.getSingleEventById(eventId);
        if (eventOptional.isPresent()) {
            SingleEvent event = eventOptional.get();
            SingleEventLongDto dto = dtoMapperService.mapToSingleEventLongDto(event);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/future")
    @Operation(summary = "Get public future events not accepted by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "Events not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong userId", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getAllPublicFutureNotAcceptedEvents(
            @RequestParam long userId,
            @Parameter(description = "To get events for few days set number of days")
            @RequestParam(required = false) Integer days) {

        List<SingleEvent> events;
        List<SingleEventShortDto> eventShortDtos;

        events = singleEventService.getAllPublicFutureNotAcceptedEvents(userId, days);

        eventShortDtos = events.stream()
                .map(dtoMapperService::mapToSingleEventShortDto)
                .collect(Collectors.toList());

        if (!events.isEmpty()) {
            return new ResponseEntity<>(eventShortDtos, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/future/accepted")
    @Operation(summary = "Get all future events accepted by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = " Events not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong userId", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getAllFutureAcceptedEvents(
            @RequestParam long userId,
            @Parameter(description = "To get events for few days set number of days")
            @RequestParam(required = false) Integer days) {

        List<SingleEvent> events;
        List<SingleEventShortDto> eventShortDtos;

        events = singleEventService.getAllFutureAcceptedEvents(userId, days);

        eventShortDtos = events.stream()
                .map(dtoMapperService::mapToSingleEventShortDto)
                .collect(Collectors.toList());

        if (!events.isEmpty()) {
            return new ResponseEntity<>(eventShortDtos, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/future/owned")
    @Operation(summary = "Get all future events owned by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "Events not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong userId", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getFutureOwnedByUser(
            @RequestParam long userId) {

        List<SingleEvent> futureOwnedEvents;
        List<SingleEventShortDto> futureOwnedEventShortDtos;

        futureOwnedEvents = singleEventService.findAllFutureOwnedByUser(userId);

        futureOwnedEventShortDtos = futureOwnedEvents.stream()
                .map(dtoMapperService::mapToSingleEventShortDto)
                .collect(Collectors.toList());

        if (!futureOwnedEvents.isEmpty()) {
            return new ResponseEntity<>(futureOwnedEventShortDtos, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/past")
    @Operation(summary = "Get all public past events where the user with given ID didn't confirm his participation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No events found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong userId", content = @Content)})
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

    @GetMapping("/past/accepted")
    @Operation(summary = "Get all past events where the user with given ID confirmed his participation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No events found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong userId", content = @Content)})
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

    @GetMapping("/past/owned")
    @Operation(summary = "Get all past events owned by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "Events not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong userId", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getPastOwnedByUser(@RequestParam long userId) {

        List<SingleEvent> pastOwnedEvents;
        List<SingleEventShortDto> pastOwnedEventShortDtos;

        pastOwnedEvents = singleEventService.findAllPastOwnedByUser(userId);

        pastOwnedEventShortDtos = pastOwnedEvents.stream()
                .map(dtoMapperService::mapToSingleEventShortDto)
                .collect(Collectors.toList());

        if (!pastOwnedEvents.isEmpty()) {
            return new ResponseEntity<>(pastOwnedEventShortDtos, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{eventId}/comments/{commentId}")
    @Operation(summary = "Get a comment by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a comment",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = EventCommentDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid Id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "event or comment not found", content = @Content)})
    public ResponseEntity<EventCommentDto> getEventComment(@PathVariable long commentId) {

        Optional<EventComment> eventCommentOptional = eventCommentService.getEventCommentById(commentId);
        if (eventCommentOptional.isPresent()) {
            EventComment eventComment = eventCommentOptional.get();
            EventCommentDto dto = dtoMapperService.mapToEventCommentDto(eventComment);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/comment/reply")
    @Operation(summary = "Reply to comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, parameters are wrong", content = @Content)})
    public ResponseEntity<Void> replyToComment(@RequestBody EventCommentDto eventCommentDto, @RequestParam long repliedCommentId) {

        EventComment eventComment = eventCommentService.replyToComment(dtoMapperService.mapToEventComment(eventCommentDto), repliedCommentId);
        if (eventComment == null) {
            return ResponseEntity.badRequest().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventComment.getCommentId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{eventId}/comment/{commentId}")
    @Operation(summary = "Create a new comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment has been created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EventCommentDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request, parameters are wrong", content = @Content)})
    public ResponseEntity<Void> addEventComment(@RequestBody EventCommentDto eventCommentDto) {
        EventComment eventComment = eventCommentService.saveNewEventComment(dtoMapperService.mapToEventComment(eventCommentDto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventComment.getCommentId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
