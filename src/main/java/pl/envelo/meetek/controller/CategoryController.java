package pl.envelo.meetek.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import pl.envelo.meetek.dto.CategoryDto;
import pl.envelo.meetek.dto.event.SingleEventLongDto;
import pl.envelo.meetek.model.Category;
import pl.envelo.meetek.service.CategoryService;
import pl.envelo.meetek.service.DtoMapperService;

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
    public ResponseEntity<CategoryDto> getCategory(@PathVariable long categoryId){
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if (category.isPresent()){
            CategoryDto categoryDto = dtoMapperService.mapToCategoryDto(category.get());
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    @Operation(summary = "Get all active categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))}),
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
