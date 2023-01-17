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

    public SingleEvent saveNewSingleEvent(SingleEvent singleEvent) {
        return singleEventRepo.save(singleEvent);
    }

    public void deleteById(long eventId) {
        singleEventRepo.deleteById(eventId);
    }

    public List<SingleEvent> getAllPublicFutureNotAcceptedEvents(long userId) {
        return singleEventRepo.findAllPublicFutureNotAcceptedByUserAll(
                LocalDateTime.now(), userId);
    }

    public List<SingleEvent> getAllPublicFutureNotAcceptedEventsForFewNearestDays(long userId, int days) {
        return singleEventRepo.findAllPublicFutureNotAcceptedByUserForFewDays(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(days),
                userId);
    }

    public List<SingleEvent> getAllEventsBeforeToday() {

        return singleEventRepo.findAllByDateTimeFromBeforeOrderByDateTimeFromDesc(LocalDateTime.now());
    }

    public List<SingleEvent> getAllEventsAfterToday() {

        return singleEventRepo.findAllByDateTimeFromAfterOrderByDateTimeFromAsc(LocalDateTime.now());
    }


    public List<SingleEvent> getAllPublicPastNotAcceptedEvents(long userId) {
        return singleEventRepo.findAllPublicPastNotAcceptedByUser(LocalDateTime.now(), userId);
    }

    public List<SingleEvent> getAllPastAcceptedEvents(long userId) {
        return singleEventRepo.findAllPastAcceptedByUser(LocalDateTime.now(), userId);
    }

}
