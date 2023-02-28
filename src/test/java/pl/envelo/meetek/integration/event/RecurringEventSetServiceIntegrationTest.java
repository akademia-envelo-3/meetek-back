package pl.envelo.meetek.integration.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.event.RecurringEventSetRepo;
import pl.envelo.meetek.domain.event.RecurringEventSetService;
import pl.envelo.meetek.domain.event.SingleEventRepo;
import pl.envelo.meetek.domain.event.model.Event;
import pl.envelo.meetek.domain.event.model.RecurringEventSet;
import pl.envelo.meetek.domain.event.model.RecurringEventSetCreateDto;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.domain.user.StandardUserRepo;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.utils.DtoMapperService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
@Transactional
public class RecurringEventSetServiceIntegrationTest {

    @Autowired
    SingleEventRepo eventRepo;

    @Autowired
    StandardUserRepo userRepo;

    @Autowired
    RecurringEventSetService eventSetService;

    @Autowired
    RecurringEventSetRepo eventSetRepo;

    @Autowired
    DtoMapperService mapperService;


    @Test
    void createRecurringEventSet_whenDataValid_Integration(){
        // given
        StandardUser user = userRepo.save(new StandardUser());
        RecurringEventSetCreateDto eventSetCreateDto = new RecurringEventSetCreateDto(
                7,
                3,
                null,
                null,
                "Name of test event",
                "https://linksail.pl/#/login",
                "testtesttesttesttesttesttesttesttesttesttest",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(3L),
                null,
                null,
                "testLocation",
                null,
                true,
                false,
                true,
                false,
                50,
                null,
                null
                );
        SingleEvent event = mapperService.mapToSingleEvent(eventSetCreateDto);
        // when
        RecurringEventSet eventSet = eventSetService.createRecurringEventSet(user, eventSetCreateDto, event);
        // then
        List<SingleEvent> eventsFromSet = new java.util.ArrayList<>(eventSet.getEvents().stream().toList());
        eventsFromSet.sort(Comparator.comparing(Event::getDateTimeFrom));

        assertThat(eventSetRepo.findById(eventSet.getEventSetId())).isPresent();

        assertThat(eventSet.getEvents().size()).isEqualTo(3);

        assertThat(eventsFromSet.get(0).getDateTimeFrom()).isEqualTo(eventSetCreateDto.getDateTimeFrom());

        assertThat(eventsFromSet.get(1).getDateTimeFrom())
                .isEqualTo(eventsFromSet.get(0).getDateTimeFrom().plusDays(eventSetCreateDto.getEventFrequency()));

        assertThat(eventsFromSet.get(2).getDateTimeFrom())
                .isEqualTo(eventsFromSet.get(1).getDateTimeFrom().plusDays(eventSetCreateDto.getEventFrequency()));

    }

}
