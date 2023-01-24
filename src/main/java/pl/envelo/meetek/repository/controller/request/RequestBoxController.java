package pl.envelo.meetek.repository.controller.request;

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
import pl.envelo.meetek.dto.request.RequestBoxDto;
import pl.envelo.meetek.model.request.RequestBox;
import pl.envelo.meetek.service.DtoMapperService;
import pl.envelo.meetek.service.request.RequestBoxService;

import java.util.Optional;

@AllArgsConstructor
@RestController
@Tag(name = "RequestBox")
@RequestMapping("/${app.prefix}/${app.version}/requestBox")
public class RequestBoxController {

    private final RequestBoxService requestBoxService;
    private final DtoMapperService dtoMapperService;

    @GetMapping("/{requestBoxId}")
    @Operation(summary = "Get a request box by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request box found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RequestBoxDto.class))}),
            @ApiResponse(responseCode = "204", description = "Request box is empty", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid requestBoxId format", content = @Content),
            @ApiResponse(responseCode = "404", description = "Request box not found", content = @Content)})
    public ResponseEntity<RequestBoxDto> getRequestBox(@PathVariable long requestBoxId) {
        Optional<RequestBox> requestBox = requestBoxService.getRequestBoxById(requestBoxId);
        if (requestBox.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        RequestBoxDto requestBoxDto = dtoMapperService.mapToRequestBoxDto(requestBox.get());
        HttpStatus status = requestBoxDto.getRequests().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(requestBoxDto, status);
    }

}
