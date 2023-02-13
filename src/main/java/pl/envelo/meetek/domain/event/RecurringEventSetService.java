package pl.envelo.meetek.domain.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.event.model.RecurringEventSet;
import pl.envelo.meetek.domain.event.model.RecurringEventSetCreateDto;
import pl.envelo.meetek.domain.event.model.RecurringEventSetDto;
import pl.envelo.meetek.utils.DtoMapperService;

@AllArgsConstructor
@Service
public class RecurringEventSetService {

    private final RecurringEventSetRepo recurringEventSetRepo;
    private final RecurringEventSetValidator recurringEventSetValidator;
    private final DtoMapperService mapperService;

    @Transactional
    public RecurringEventSetDto createRecurringEventSet(RecurringEventSetCreateDto recurringEventSetDto) {
        RecurringEventSet recurringEventSet = mapperService.mapToRecurringEventSet(recurringEventSetDto);
        recurringEventSetValidator.validateInput(recurringEventSet);
        recurringEventSet = recurringEventSetRepo.save(recurringEventSet);
        return mapperService.mapToRecurringEventSetDto(recurringEventSet);
    }

}
