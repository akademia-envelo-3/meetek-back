package pl.envelo.meetek.service.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.repository.event.SingleEventRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SingleEventService {

    private final SingleEventRepo singleEventRepo;

    public Optional<SingleEvent> getSingleEventById(long id) {
        return singleEventRepo.findById(id);
    }

    public List<SingleEvent> getAllPublicFutureNotAcceptedEvents(long userId) {

        return singleEventRepo.findAllPublicFutureNotAcceptedByUserAll(
                //LocalDateTime.now(),userId);
                LocalDateTime.of(2022, 8, 17, 20, 47, 52, 6),
                userId);
    }

    public List<SingleEvent> getAllPublicFutureNotAcceptedEventsForFewNearestDays(long userId, int days) {

        //LocalDateTime timeTo = LocalDateTime.now().plusDays(days);
        LocalDateTime timeTo = LocalDateTime.of(2022, 8, 17, 20, 47, 52, 6).plusDays(days);

        return singleEventRepo.findAllPublicFutureNotAcceptedByUserForFewDays(
                LocalDateTime.of(2022, 8, 17, 20, 47, 52, 6),
                timeTo,
                userId);
    }
}
