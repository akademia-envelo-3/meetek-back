package pl.envelo.meetek.domain.event;

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
import pl.envelo.meetek.domain.comment.model.EventCommentCreateDto;
import pl.envelo.meetek.domain.comment.model.EventCommentDto;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.domain.event.model.SingleEventCreateDto;
import pl.envelo.meetek.domain.event.model.SingleEventLongDto;
import pl.envelo.meetek.domain.event.model.SingleEventShortDto;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.domain.comment.EventCommentService;
import pl.envelo.meetek.domain.user.StandardUserService;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Event")
@RequestMapping("/${app.prefix}/${app.version}/events")
public class SingleEventController {

    private final SingleEventService singleEventService;
    private final EventCommentService eventCommentService;
    private final StandardUserService standardUserService;

    @PostMapping
    @Operation(summary = "Create new event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong parameters", content = @Content)})
    public ResponseEntity<Void> createEvent(@RequestParam long userId, @RequestBody SingleEventCreateDto eventDto) {
        StandardUser user = standardUserService.getStandardUserById(userId);
        SingleEventShortDto event = singleEventService.createEvent(user, eventDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(event.getEventId())
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
        singleEventService.deleteById(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{eventId}")
    @Operation(summary = "Get event by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SingleEventLongDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)})
    public ResponseEntity<SingleEventLongDto> getEvent(@PathVariable long eventId) {
        SingleEventLongDto event = singleEventService.getEventById(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all events responded by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No event found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong userId", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getAllRespondedEvents(@RequestParam long userId, @RequestParam String time, @RequestParam String response) {
        StandardUser validatedUser = standardUserService.getStandardUserById(userId);
        List<SingleEventShortDto> events = singleEventService.getAllRespondedEvents(validatedUser.getParticipantId(), time, response);
        HttpStatus httpStatus = events.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(events, httpStatus);
    }

    @GetMapping("/all")
    @Operation(summary = "Get public events not responded by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No event found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong userId", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getAllPublicNotRespondedEvents(@RequestParam long userId, @RequestParam String time) {
        StandardUser validatedUser = standardUserService.getStandardUserById(userId);
        List<SingleEventShortDto> events = singleEventService.getAllPublicNotRespondedEvents(validatedUser.getParticipantId(), time);
        HttpStatus status = events.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(events, status);
    }

    @GetMapping("/owned")
    @Operation(summary = "Get all events owned by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No event found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong userId", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getOwnedByUser(@RequestParam long userId, @RequestParam String time) {
        StandardUser validatedUser = standardUserService.getStandardUserById(userId);
        List<SingleEventShortDto> events = singleEventService.getAllOwnedByUser(validatedUser.getParticipantId(), time);
        HttpStatus status = events.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(events, status);
    }

     @GetMapping("/{eventId}/comments/{commentId}")
    @Operation(summary = "Get comment by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment found",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = EventCommentDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event or comment not found", content = @Content)})
    public ResponseEntity<EventCommentDto> getEventComment(@PathVariable long commentId) {
        EventCommentDto comment = eventCommentService.getEventCommentById(commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/{eventId}/comments")
    @Operation(summary = "Create new comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EventCommentCreateDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong parameters wrong", content = @Content)})
    public ResponseEntity<Void> addEventComment(@PathVariable long eventId, @RequestParam long userId, @RequestParam(required = false) Long commentedCommentId, @RequestBody EventCommentCreateDto eventCommentDto) {
        StandardUser user = standardUserService.getStandardUserById(userId);
        SingleEvent event = singleEventService.getEventByIdNotDto(eventId);
        EventCommentDto eventComment = eventCommentService.createEventComment(user, event, commentedCommentId, eventCommentDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventComment.getCommentId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
