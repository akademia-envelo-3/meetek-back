package pl.envelo.meetek.controller.category;

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
import pl.envelo.meetek.dto.category.CategoryDto;
import pl.envelo.meetek.model.category.Category;
import pl.envelo.meetek.service.category.CategoryService;
import pl.envelo.meetek.service.DtoMapperService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@Tag(name = "Category")
@RequestMapping("/${app.prefix}/${app.version}/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final DtoMapperService dtoMapperService;

    @GetMapping("/{categoryId}")
    @Operation(summary = "Get a category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid categoryId format", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)})
    public ResponseEntity<CategoryDto> getCategory(@PathVariable long categoryId) {
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if (category.isPresent()) {
            CategoryDto categoryDto = dtoMapperService.mapToCategoryDto(category.get());
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong parameters", content = @Content)})
    public ResponseEntity<Void> saveNewCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.saveNewCategory(dtoMapperService.mapToCategory(categoryDto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getCategoryId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{categoryId}")
    @Operation(summary = "Edit category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, wrong parameters", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)})
    public ResponseEntity<Void> editCategory(@PathVariable long categoryId, @RequestBody CategoryDto categoryDto) {
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if (category.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Category updatedCategory = dtoMapperService.mapToCategory(categoryDto);
        categoryService.editCategory(categoryId, updatedCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "Get all active categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))}),
            @ApiResponse(responseCode = "204", description = "No category found", content = @Content)})
    public ResponseEntity<List<CategoryDto>> getActiveCategories() {
        List<Category> categories = categoryService.getAllActiveCategories();
        List<CategoryDto> dtoCategories = categories.stream()
                .map(dtoMapperService::mapToCategoryDto)
                .toList();
        HttpStatus status = dtoCategories.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(dtoCategories, status);
    }

}
