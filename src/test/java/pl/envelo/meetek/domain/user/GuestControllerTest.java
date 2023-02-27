package pl.envelo.meetek.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.envelo.meetek.domain.category.CategoryDto;
import pl.envelo.meetek.domain.user.model.GuestDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestControllerTest {

    @Mock
    private GuestService guestService;

    @InjectMocks
    private GuestController guestController;

    @Test
    public void testGetGuest_ReturnSuccessfulAndOkStatus() {
        long guestId = 1L;
        GuestDto guestDto = new GuestDto();
        guestDto.setParticipantId(guestId);

        when(guestService.getGuestById(guestId)).thenReturn(guestDto);

        ResponseEntity<GuestDto> response = guestController.getGuest(guestId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(guestDto, response.getBody());
    }

}