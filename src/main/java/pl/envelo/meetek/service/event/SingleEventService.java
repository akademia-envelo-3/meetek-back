package pl.envelo.meetek.service.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.repository.event.SingleEventRepo;
import pl.envelo.meetek.service.survey.SurveyService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SingleEventService {

    private final SingleEventRepo singleEventRepo;
    private final SurveyService surveyService;

    @Transactional(readOnly = true)
    public Optional<SingleEvent> getSingleEventById(long id) {
        Optional<SingleEvent> singleEventOptional = singleEventRepo.findById(id);
        if(singleEventOptional.isPresent()){
            if(!singleEventOptional.get().getSurveys().isEmpty()) {
                singleEventOptional.get().getSurveys()
                        .forEach(surveyService::setSurveyFields);
            }
        }
        return singleEventOptional;
    }

    @Transactional
    public SingleEvent saveNewSingleEvent(SingleEvent singleEvent) {
        return singleEventRepo.save(singleEvent);
    }

    @Transactional
    public void deleteById(long eventId) {
        singleEventRepo.deleteById(eventId);
    }

    @Transactional(readOnly = true)
    public List<SingleEvent> getAllPublicFutureNotAcceptedEvents(long userId) {
        return singleEventRepo.findAllPublicFutureNotAcceptedByUserAll(
                LocalDateTime.now(), userId);
    }

    @Transactional(readOnly = true)
    public List<SingleEvent> getAllPublicFutureNotAcceptedEventsForFewNearestDays(long userId, int days) {
        return singleEventRepo.findAllPublicFutureNotAcceptedByUserForFewDays(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(days),
                userId);
    }

    @Transactional(readOnly = true)
    public List<SingleEvent> getAllEventsBeforeToday() {
        return singleEventRepo.findAllByDateTimeFromBeforeOrderByDateTimeFromDesc(LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public List<SingleEvent> getAllEventsAfterToday() {
        return singleEventRepo.findAllByDateTimeFromAfterOrderByDateTimeFromAsc(LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public List<SingleEvent> getAllPublicPastNotAcceptedEvents(long userId) {
        return singleEventRepo.findAllPublicPastNotAcceptedByUser(LocalDateTime.now(), userId);
    }

    @Transactional(readOnly = true)
    public List<SingleEvent> getAllPastAcceptedEvents(long userId) {
        return singleEventRepo.findAllPastAcceptedByUser(LocalDateTime.now(), userId);
    }

    @Transactional(readOnly = true)
    public List<SingleEvent> findAllPastOwnedByUser(long userId) {
        return singleEventRepo.findPastOwnedByUser(LocalDateTime.now(), userId);
    }

    @Transactional(readOnly = true)
    public List<SingleEvent> getAllFutureAcceptedEvents(long userId) {
        return singleEventRepo.findAllFutureAccepted(LocalDateTime.now(), userId);
    }

    @Transactional(readOnly = true)
    public List<SingleEvent> getAllFutureAcceptedEventsForFewNearestDays(long userId, int days) {
        return singleEventRepo.findAllFutureAcceptedForFewNearestDays(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(days),
                userId);
    }

    @Transactional(readOnly = true)
    public List<SingleEvent> findAllFutureOwnedByUser(long userId) {
        return singleEventRepo.findFutureOwnedByUser(LocalDateTime.now(), userId);
    }

}
