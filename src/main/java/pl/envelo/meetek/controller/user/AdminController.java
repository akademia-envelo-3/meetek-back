package pl.envelo.meetek.controller.user;

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
import pl.envelo.meetek.dto.category.CategoryDto;
import pl.envelo.meetek.dto.hashtag.HashtagDto;
import pl.envelo.meetek.dto.event.SingleEventShortDto;
import pl.envelo.meetek.dto.request.CategoryRequestDto;
import pl.envelo.meetek.model.request.RequestStatus;
import pl.envelo.meetek.model.category.Category;
import pl.envelo.meetek.model.hashtag.Hashtag;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.model.request.CategoryRequest;
import pl.envelo.meetek.service.category.CategoryService;
import pl.envelo.meetek.service.DtoMapperService;
import pl.envelo.meetek.service.hashtag.HashtagService;
import pl.envelo.meetek.service.event.SingleEventService;
import pl.envelo.meetek.service.request.CategoryRequestService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Tag(name = "Admin")
@RequestMapping("/${app.prefix}/${app.version}/admin")
public class AdminController {

    private SingleEventService singleEventService;
    private final CategoryService categoryService;
    private final HashtagService hashtagService;
    private final CategoryRequestService categoryRequestService;
    private DtoMapperService dtoMapperService;

    @GetMapping("/past-events")
    @Operation(summary = "Get all events that started before current dateTime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found events",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No events found", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getAllEventsBeforeToday() {

        List<SingleEvent> pastEvents;
        List<SingleEventShortDto> pastEventsShortDto;

        pastEvents = singleEventService.getAllEventsBeforeToday();

        pastEventsShortDto = pastEvents.stream().map(singleEvent -> dtoMapperService.
                mapToSingleEventShortDto(singleEvent)).collect(Collectors.toList());

        if (pastEvents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(pastEventsShortDto, HttpStatus.OK);
        }
    }

    @GetMapping("/future-events")
    @Operation(summary = "Get all events that started after current dateTime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found events",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No events found", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getAllEventsAfterToday() {

        List<SingleEvent> futureEvents;
        List<SingleEventShortDto> futureEventsShortDto;

        futureEvents = singleEventService.getAllEventsAfterToday();

        futureEventsShortDto = futureEvents.stream().map(singleEvent -> dtoMapperService.
                mapToSingleEventShortDto(singleEvent)).collect(Collectors.toList());

        if (futureEvents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(futureEventsShortDto, HttpStatus.OK);
        }
    }

    @GetMapping("/categories")
    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No category found", content = @Content)})
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> dtoCategories = categories.stream()
                .map(dtoMapperService::mapToCategoryDto)
                .toList();
        HttpStatus status = dtoCategories.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(dtoCategories, status);
    }

    @GetMapping("/hashtags")
    @Operation(summary = "Get all hashtags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = HashtagDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No hashtag found", content = @Content)})
    public ResponseEntity<List<HashtagDto>> getAllHashtags() {
        List<Hashtag> hashtags = hashtagService.getAllHashtags();
        List<HashtagDto> dtoHashtags = hashtags.stream()
                .map(dtoMapperService::mapToHashtagDto)
                .toList();
        HttpStatus status = dtoHashtags.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(dtoHashtags, status);
    }

    @GetMapping("/category-requests")
    @Operation(summary = "Get all not processed category requests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CategoryRequestDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No category request found", content = @Content)})
    public ResponseEntity<List<CategoryRequestDto>> getAllNotProcessedCategoryRequests() {
        List<CategoryRequest> requests = categoryRequestService.getAllNotProcessedRequests();
        List<CategoryRequestDto> requestsDto = requests.stream()
                .map(dtoMapperService::mapToCategoryRequestDto)
                .toList();
        HttpStatus status = requestsDto.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(requestsDto, status);
    }

    @PostMapping("/category-requests/{categoryRequestId}")
    @Operation(summary = "Reply to a category request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category request found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryRequestDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid categoryRequestId format / incorrect requestStatus / in case of rejection - empty comment", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category request not found", content = @Content)})
    public ResponseEntity<?> replyToCategoryRequest(@PathVariable long categoryRequestId, @RequestBody CategoryRequestDto categoryRequestDto) {
        Optional<CategoryRequest> categoryRequest = categoryRequestService.getCategoryRequestById(categoryRequestId);
        if (categoryRequest.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category request not found");
        }
        if (categoryRequest.get().getStatus() != RequestStatus.NOT_PROCESSED) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category request has already been processed");
        }
        Optional<RequestStatus> convertedStatus = Arrays.stream(RequestStatus.values())
                .filter(s -> s.toString().equals(categoryRequestDto.getRequestStatus()))
                .findFirst();
        if (convertedStatus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong status, values accepted: " + RequestStatus.ACCEPTED + ", " + RequestStatus.REJECTED);
        }
        RequestStatus requestStatus = convertedStatus.get();
        if (requestStatus == RequestStatus.REJECTED) {
            if (categoryRequestDto.getComment() == null || categoryRequestDto.getComment().getComment().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comment cannot be empty");
            }
        }
        CategoryRequest convertedRequest = dtoMapperService.mapToCategoryRequest(categoryRequestDto);
        categoryRequestService.replyToRequest(convertedRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
