package pl.envelo.meetek.domain.user;

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
import pl.envelo.meetek.domain.category.CategoryDto;
import pl.envelo.meetek.domain.category.CategoryService;
import pl.envelo.meetek.domain.event.SingleEventService;
import pl.envelo.meetek.domain.event.model.SingleEventShortDto;
import pl.envelo.meetek.domain.hashtag.HashtagDto;
import pl.envelo.meetek.domain.hashtag.HashtagService;
import pl.envelo.meetek.domain.request.CategoryRequestService;
import pl.envelo.meetek.domain.request.model.CategoryRequestDto;
import pl.envelo.meetek.domain.user.model.Admin;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Admin")
@RequestMapping("/${app.prefix}/${app.version}/admin")
public class AdminController {

    private SingleEventService singleEventService;
    private final CategoryService categoryService;
    private final HashtagService hashtagService;
    private final CategoryRequestService categoryRequestService;
    private final AdminService adminService;

    @GetMapping("/events/past")
    @Operation(summary = "Get all events that started before current dateTime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No event found", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getAllEventsBeforeToday() {
        List<SingleEventShortDto> events = singleEventService.getAllEventsBeforeToday();
        HttpStatus status = events.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(events, status);
    }

    @GetMapping("/events/future")
    @Operation(summary = "Get all events that started after current dateTime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SingleEventShortDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No event found", content = @Content)})
    public ResponseEntity<List<SingleEventShortDto>> getAllEventsAfterToday() {
        List<SingleEventShortDto> events = singleEventService.getAllEventsAfterToday();
        HttpStatus status = events.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(events, status);
    }

    @GetMapping("/hashtags")
    @Operation(summary = "Get all hashtags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = HashtagDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No hashtag found", content = @Content)})
    public ResponseEntity<List<HashtagDto>> getAllHashtags() {
        List<HashtagDto> hashtags = hashtagService.getAllHashtags();
        HttpStatus status = hashtags.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(hashtags, status);
    }

    @GetMapping("/categories")
    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No category found", content = @Content)})
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        HttpStatus status = categories.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(categories, status);
    }

    @GetMapping("/category-requests")
    @Operation(summary = "Get all not processed category requests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CategoryRequestDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No category request found", content = @Content)})
    public ResponseEntity<List<CategoryRequestDto>> getAllNotProcessedCategoryRequests() {
        List<CategoryRequestDto> requests = categoryRequestService.getAllNotProcessedRequests();
        HttpStatus status = requests.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(requests, status);
    }

    @PostMapping("/category-requests/{categoryRequestId}")
    @Operation(summary = "Reply to category request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category request found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryRequestDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid categoryRequestId format / incorrect requestStatus / in case of rejection - empty comment", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category request not found", content = @Content)})
    public ResponseEntity<Void> replyToCategoryRequest(@PathVariable long categoryRequestId, @RequestParam long userId, @RequestBody CategoryRequestDto categoryRequestDto) {
        Admin admin = adminService.getById(userId);
        categoryRequestService.replyToRequest(categoryRequestId, admin, categoryRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
