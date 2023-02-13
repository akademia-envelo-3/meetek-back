package pl.envelo.meetek.domain.user;

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
import pl.envelo.meetek.domain.user.model.GuestDto;

@AllArgsConstructor
@RestController
@Tag(name = "Guest")
@RequestMapping("/${app.prefix}/${app.version}/guests")
public class GuestController {

    private final GuestService guestService;

    @GetMapping("/{guestId}")
    @Operation(summary = "Get guest by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GuestDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Guest not found", content = @Content)})
    public ResponseEntity<GuestDto> getGuest(@PathVariable long guestId) {
        GuestDto guest = guestService.getGuestById(guestId);
        return new ResponseEntity<>(guest, HttpStatus.OK);
    }

}
