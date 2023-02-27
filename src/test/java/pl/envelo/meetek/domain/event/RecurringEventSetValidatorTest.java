package pl.envelo.meetek.domain.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.event.model.RecurringEventSet;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.domain.user.StandardUserValidator;
import pl.envelo.meetek.exceptions.NotFoundException;

import javax.xml.validation.Validator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecurringEventSetValidatorTest {

    @Mock
    private RecurringEventSetRepo recurringEventSetRepo;

    @Mock
    private StandardUserValidator userValidator;

    @Mock
    private Validator validator;

    @InjectMocks
    private RecurringEventSetValidator recurringEventSetValidator;

    @Test
    public void testValidateExists() {
        long eventSetId = 1L;
        RecurringEventSet event = new RecurringEventSet();
        event.setEventSetId(eventSetId);

        when(recurringEventSetRepo.findById(eventSetId)).thenReturn(Optional.of(event));

        RecurringEventSet validatedEvent = recurringEventSetValidator.validateExists(eventSetId);

        assertEquals(event, validatedEvent);
    }

    @Test
    public void testValidateExists_WhennotFound() {
        when(recurringEventSetRepo.findById(1L)).thenReturn(Optional.empty());
        RecurringEventSetValidator validator = new RecurringEventSetValidator(null, recurringEventSetRepo);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> validator.validateExists(1L));

        assertEquals("Recurring event set with id 1 not found", exception.getMessage());
    }

}