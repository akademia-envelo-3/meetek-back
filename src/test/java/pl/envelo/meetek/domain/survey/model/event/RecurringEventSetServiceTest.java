package pl.envelo.meetek.domain.survey.model.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.event.RecurringEventSetRepo;
import pl.envelo.meetek.domain.event.RecurringEventSetService;
import pl.envelo.meetek.domain.event.RecurringEventSetValidator;
import pl.envelo.meetek.domain.event.model.RecurringEventSet;
import pl.envelo.meetek.domain.event.model.RecurringEventSetCreateDto;
import pl.envelo.meetek.domain.event.model.RecurringEventSetDto;
import pl.envelo.meetek.utils.DtoMapperService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecurringEventSetServiceTest {

    @Mock
    private RecurringEventSetRepo recurringEventSetRepo;

    @Mock
    private RecurringEventSetValidator recurringEventSetValidator;

    @Mock
    private DtoMapperService mapperService;

    @InjectMocks
    private RecurringEventSetService recurringEventSetService;


/*    @Test
    public void testCreateRecurringEventSet_ReturnSuccessful() {
        RecurringEventSetCreateDto recurringEventSetCreateDto = new RecurringEventSetCreateDto();
        RecurringEventSet recurringEventSet = new RecurringEventSet();
        RecurringEventSetDto recurringEventSetDto = new RecurringEventSetDto();
        when(mapperService.mapToRecurringEventSet(recurringEventSetCreateDto)).thenReturn(recurringEventSet);
        when(recurringEventSetRepo.save(recurringEventSet)).thenReturn(recurringEventSet);
        when(mapperService.mapToRecurringEventSetDto(recurringEventSet)).thenReturn(recurringEventSetDto);

        RecurringEventSetDto result = recurringEventSetService.createRecurringEventSet(recurringEventSetCreateDto);

        verify(recurringEventSetValidator).validateInput(recurringEventSet);
        verify(recurringEventSetRepo).save(recurringEventSet);
        verify(mapperService).mapToRecurringEventSetDto(recurringEventSet);
        assertEquals(recurringEventSetDto, result);
    }*/
}