package pl.envelo.meetek.domain.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.event.model.*;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class RecurringEventSetService {

    private final RecurringEventSetRepo recurringEventSetRepo;
    private final RecurringEventSetValidator recurringEventSetValidator;
    private final DtoMapperService mapperService;
    private final SingleEventService eventService;


    @Transactional
    public RecurringEventSet createRecurringEventSet( StandardUser owner, RecurringEventSetCreateDto eventSetCreateDto, SingleEvent event){
        RecurringEventSet eventSet = mapperService.mapToRecurringEventSet(eventSetCreateDto);
        recurringEventSetValidator.validateInput(eventSet);
        eventSet = recurringEventSetRepo.save(eventSet);
        SingleEventCreateDto eventCreateDto = mapperService.mapToSingleEventCreateDto(event);
        Set<SingleEvent> singleEvents = new HashSet<>();

        for(int i = 0; i < eventSet.getRecursiveCount(); i++) {
            if(!singleEvents.isEmpty()) {
                eventCreateDto.setDateTimeFrom(eventCreateDto.getDateTimeFrom().plusDays(eventSet.getEventFrequency()));
                eventCreateDto.setDateTimeTo(eventCreateDto.getDateTimeTo().plusDays(eventSet.getEventFrequency()));
            }
            SingleEvent singleEvent = mapperService.mapToSingleEvent(eventService.createEvent(owner,eventCreateDto));
            singleEvents.add(singleEvent);
        }
        eventSet.setEvents(singleEvents);
        return eventSet;
    }

}
