package pl.envelo.meetek.domain.event;

import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.domain.user.StandardUserValidator;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.exceptions.DuplicateException;
import pl.envelo.meetek.exceptions.NotFoundException;

import javax.xml.validation.Validator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SingleEventValidatorTest {

    @Mock
    private SingleEventRepo singleEventRepo;

    @Mock
    private StandardUserValidator userValidator;

    @Mock
    private Validator validator;

    @InjectMocks
    private SingleEventValidator singleEventValidator;

    @Test
    public void testValidateExists() {
        long eventId = 1L;
        SingleEvent event = new SingleEvent();
        event.setEventId(eventId);

        when(singleEventRepo.findById(eventId)).thenReturn(Optional.of(event));

        SingleEvent validatedEvent = singleEventValidator.validateExists(eventId);

        assertEquals(event, validatedEvent);
    }

    @Test
    public void testValidateExists_notFound() {
        when(singleEventRepo.findById(1L)).thenReturn(Optional.empty());
        SingleEventValidator validator = new SingleEventValidator(null, singleEventRepo, userValidator);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> validator.validateExists(1L));

        assertEquals("Event with id 1 not found", exception.getMessage());
    }

    @Test
    public void testValidateDaysCount_null() {
        singleEventValidator = new SingleEventValidator(null, singleEventRepo, userValidator);
        Integer days = null;
        Integer result = singleEventValidator.validateDaysCount(days);
        assertEquals(null, result);
    }

    @Test
    public void testValidateDaysCount_zero() {
        singleEventValidator = new SingleEventValidator(null, singleEventRepo, userValidator);
        Integer days = 0;
        Integer result = singleEventValidator.validateDaysCount(days);
        assertEquals(1, result);
    }

    @Test
    public void testValidateDaysCount_positiveDayCount() {
        singleEventValidator = new SingleEventValidator(null, singleEventRepo, userValidator);
        Integer days = 5;
        Integer result = singleEventValidator.validateDaysCount(days);
        assertEquals(days, result);
    }


    @Test
    public void testValidateOwnerForAdmin_ownerIdAlreadyExists() {
        SingleEvent event = new SingleEvent();
        StandardUser owner = new StandardUser();
        owner.setParticipantId(1L);
        event.setOwner(owner);
        long newOwnerId = 1L;

        DuplicateException thrown = assertThrows(DuplicateException.class, () -> {
            singleEventValidator.validateOwnerForAdmin(event, newOwnerId);
        });
        assertEquals("User with id " + newOwnerId + " is already the owner of this event", thrown.getMessage());
    }

    @Test
    public void testValidateOwnerForAdmin_validNewOwnerId() {
        SingleEvent event = new SingleEvent();
        StandardUser owner = new StandardUser();
        owner.setParticipantId(1L);
        event.setOwner(owner);
        long newOwnerId = 2L;

        StandardUser newOwner = new StandardUser();
        newOwner.setParticipantId(newOwnerId);
        when(userValidator.validateExists(newOwnerId)).thenReturn(newOwner);

        StandardUser result = singleEventValidator.validateOwnerForAdmin(event, newOwnerId);
        assertEquals(newOwnerId, result.getParticipantId());
        verify(userValidator).validateExists(newOwnerId);
    }
}
