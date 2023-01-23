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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.envelo.meetek.dto.request.CategoryRequestDto;
import pl.envelo.meetek.model.request.CategoryRequest;
import pl.envelo.meetek.service.DtoMapperService;
import pl.envelo.meetek.service.request.CategoryRequestService;

import java.util.Optional;

@AllArgsConstructor
@RestController
@Tag(name = "Request")
@RequestMapping("/${app.prefix}/${app.version}/requests")
public class RequestController {

    private final CategoryRequestService categoryRequestService;
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

}
