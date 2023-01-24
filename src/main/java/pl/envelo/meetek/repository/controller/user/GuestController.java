package pl.envelo.meetek.repository.controller.user;

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
import pl.envelo.meetek.dto.user.AdminDto;
import pl.envelo.meetek.dto.user.GuestDto;
import pl.envelo.meetek.model.user.Guest;
import pl.envelo.meetek.service.DtoMapperService;
import pl.envelo.meetek.service.user.GuestService;

import java.util.Optional;

@AllArgsConstructor
@RestController
@Tag(name = "Guest")
@RequestMapping("/${app.prefix}/${app.version}/guest")
public class GuestController {

    private final GuestService guestService;
    private final DtoMapperService dtoMapperService;

    @GetMapping("/{guestId}")
    @Operation(summary = "Get an guest by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the guest",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AdminDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Guest not found", content = @Content)})
    public ResponseEntity getGuest(@PathVariable long guestId) {
        Optional<Guest> guest = guestService.getGuestById(guestId);
        if (guest.isPresent()) {
            GuestDto guestDto = dtoMapperService.mapToGuestDto(guest.get());

            return new ResponseEntity(guestDto, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

}
