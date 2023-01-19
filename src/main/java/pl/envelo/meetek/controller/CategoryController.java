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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.envelo.meetek.dto.CategoryDto;
import pl.envelo.meetek.model.Category;
import pl.envelo.meetek.service.CategoryService;
import pl.envelo.meetek.service.DtoMapperService;

import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Category")
@RequestMapping("/${app.prefix}/${app.version}/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final DtoMapperService dtoMapperService;

    @GetMapping()
    @Operation(summary = "Get all categories (admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Results returned",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))}),
            @ApiResponse(responseCode = "204", description = "No category found", content = @Content)})
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> dtoCategories = categories.stream()
                .map(dtoMapperService::mapToCategoryDto)
                .toList();
        HttpStatus status = dtoCategories.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(dtoCategories, status);
    }

}
