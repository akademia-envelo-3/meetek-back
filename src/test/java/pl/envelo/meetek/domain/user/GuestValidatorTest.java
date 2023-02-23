package pl.envelo.meetek.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.user.model.Admin;
import pl.envelo.meetek.domain.user.model.Guest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestValidatorTest {

    @Mock
    private GuestRepo guestRepo;

    @InjectMocks
    private GuestValidator guestValidator;

    @Test
    void testValidateIfAdminExists() {
        Guest guest = new Guest();
        guest.setParticipantId(1L);

        when(guestRepo.findById(1L)).thenReturn(Optional.of(guest));
        GuestValidator validator = new GuestValidator(null, guestRepo);

        Guest validatedGuest = validator.validateExists(1L);

        assertEquals(guest, validatedGuest);
    }
}