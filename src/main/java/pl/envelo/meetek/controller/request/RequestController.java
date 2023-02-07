package pl.envelo.meetek.controller.request;

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
import pl.envelo.meetek.dto.request.CategoryRequestCreateDto;
import pl.envelo.meetek.dto.request.CategoryRequestDto;
import pl.envelo.meetek.model.request.RequestStatus;
import pl.envelo.meetek.model.category.Category;
import pl.envelo.meetek.model.request.CategoryRequest;
import pl.envelo.meetek.model.user.StandardUser;
import pl.envelo.meetek.service.DtoMapperService;
import pl.envelo.meetek.service.attachment.category.CategoryService;
import pl.envelo.meetek.service.request.CategoryRequestService;
import pl.envelo.meetek.service.user.StandardUserService;

import java.net.URI;
import java.util.Optional;

@AllArgsConstructor
@RestController
@Tag(name = "Request")
@RequestMapping("/${app.prefix}/${app.version}/requests")
public class RequestController {

    private final CategoryRequestService categoryRequestService;
    private final CategoryService categoryService;
    private final StandardUserService standardUserService;
    private final DtoMapperService dtoMapperService;

    @GetMapping("/{categoryRequestId}")
    @Operation(summary = "Get a category request by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category request found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryRequestDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid categoryRequestId format", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category request not found", content = @Content)})
    public ResponseEntity<CategoryRequestDto> getCategoryRequest(@PathVariable long categoryRequestId) {
        Optional<CategoryRequest> categoryRequest = categoryRequestService.getCategoryRequestById(categoryRequestId);
        if (categoryRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CategoryRequestDto categoryRequestDto = dtoMapperService.mapToCategoryRequestDto(categoryRequest.get());
        return new ResponseEntity<>(categoryRequestDto, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new category request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category request created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request - wrong parameters or category already exists", content = @Content)})
    public ResponseEntity<String> createCategoryRequest(@RequestParam long userId, @RequestBody CategoryRequestCreateDto categoryRequestCreateDto) {
        Optional<StandardUser> standardUserOptional = standardUserService.getStandardUserById(userId);
        if(standardUserOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");}

        Optional<Category> category = categoryService.getCategoryByName(categoryRequestCreateDto.getName());
        CategoryRequest categoryRequest = dtoMapperService.mapToCategoryRequest(categoryRequestCreateDto);
        categoryRequest.setCategory(null);
        if (category.isPresent()) {
            if (category.get().isActive()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category already exists");
            } else {
                categoryRequest.setCategory(category.get());
            }
        }
        CategoryRequest request = categoryRequestService.createCategoryRequest(standardUserOptional.get(), categoryRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getRequestId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
