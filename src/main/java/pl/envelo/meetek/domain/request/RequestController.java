package pl.envelo.meetek.domain.request;

import io.swagger.v3.oas.annotations.Operation;
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
import pl.envelo.meetek.domain.request.model.CategoryRequestCreateDto;
import pl.envelo.meetek.domain.request.model.CategoryRequestDto;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.domain.user.StandardUserService;

import java.net.URI;
import java.util.Optional;

@AllArgsConstructor
@RestController
@Tag(name = "Request")
@RequestMapping("/${app.prefix}/${app.version}/requests")
public class RequestController {

    private final CategoryRequestService categoryRequestService;
    private final StandardUserService standardUserService;

    @GetMapping("/{categoryRequestId}")
    @Operation(summary = "Get category request by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category request found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryRequestDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid categoryRequestId format", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category request not found", content = @Content)})
    public ResponseEntity<CategoryRequestDto> getCategoryRequest(@PathVariable long categoryRequestId) {
        CategoryRequestDto categoryRequest = categoryRequestService.getCategoryRequestById(categoryRequestId);
        return new ResponseEntity<>(categoryRequest, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create new category request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category request created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request - wrong parameters or category already exists", content = @Content)})
    public ResponseEntity<String> createCategoryRequest(@RequestParam long userId, @RequestBody CategoryRequestCreateDto categoryRequestCreateDto) {
        Optional<StandardUser> standardUserOptional = standardUserService.getStandardUserById(userId);
        if (standardUserOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        StandardUser standardUser = standardUserOptional.get();

        CategoryRequestDto categoryRequestDto = categoryRequestService.createCategoryRequest(standardUser, categoryRequestCreateDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryRequestDto.getRequestId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
