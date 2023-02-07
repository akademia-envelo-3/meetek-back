package pl.envelo.meetek.service.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.model.hashtag.Hashtag;
import pl.envelo.meetek.model.survey.Survey;
import pl.envelo.meetek.model.user.StandardUser;
import pl.envelo.meetek.repository.event.SingleEventRepo;
import pl.envelo.meetek.service.comment.EventCommentService;
import pl.envelo.meetek.service.hashtag.HashtagService;
import pl.envelo.meetek.service.survey.SurveyService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SingleEventService {

    private final SingleEventRepo singleEventRepo;
    private final SurveyService surveyService;
    private final EventCommentService eventCommentService;
    private final HashtagService hashtagService;

    @Transactional(readOnly = true)
    public Optional<SingleEvent> getSingleEventById(long id) {
        Optional<SingleEvent> singleEventOptional = singleEventRepo.findById(id);
        if (singleEventOptional.isPresent()) {
            if (!singleEventOptional.get().getSurveys().isEmpty()) {
                singleEventOptional.get().getSurveys()
                        .forEach(surveyService::setSurveyFields);
            }
        }
        return singleEventOptional;
    }

    @Transactional
    public SingleEvent saveNewSingleEvent(StandardUser standardUser, SingleEvent singleEvent) {
        singleEvent.setOwner(standardUser);
        return singleEventRepo.save(singleEvent);
    }

    @Transactional
    public void deleteById(long eventId) {
        singleEventRepo.deleteById(eventId);
    }

    public List<SingleEvent> getAllPublicFutureNotAcceptedEvents(long userId, Integer days) {
        Integer validatedDays = validateDaysCount(days);
        if (validatedDays == null) {
            return getAllPublicFutureNotAcceptedEvents(userId);
        } else {
            return getAllPublicFutureNotAcceptedEventsForFewNearestDays(userId, days);
        }
    }

    @Transactional(readOnly = true)
    private List<SingleEvent> getAllPublicFutureNotAcceptedEvents(long userId) {
        return singleEventRepo.findAllPublicFutureNotAcceptedByUserAll(
                LocalDateTime.now(), userId);
    }

    @Transactional(readOnly = true)
    private List<SingleEvent> getAllPublicFutureNotAcceptedEventsForFewNearestDays(long userId, int days) {
        return singleEventRepo.findAllPublicFutureNotAcceptedByUserForFewDays(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(days),
                userId);
    }

    @Transactional
    private void addSurvey(Survey survey) {
        surveyService.createSurvey(survey);
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

    public List<SingleEvent> getAllFutureAcceptedEvents(long userId, Integer days) {
        Integer validatedDays = validateDaysCount(days);
        if (validatedDays == null) {
            return getAllFutureAcceptedEvents(userId);
        } else {
            return getAllFutureAcceptedEventsForFewNearestDays(userId, validatedDays);
        }
    }

    @Transactional(readOnly = true)
    private List<SingleEvent> getAllFutureAcceptedEvents(long userId) {
        return singleEventRepo.findAllFutureAccepted(LocalDateTime.now(), userId);
    }

    @Transactional(readOnly = true)
    private List<SingleEvent> getAllFutureAcceptedEventsForFewNearestDays(long userId, int days) {
        return singleEventRepo.findAllFutureAcceptedForFewNearestDays(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(days),
                userId);
    }

    @Transactional(readOnly = true)
    public List<SingleEvent> findAllFutureOwnedByUser(long userId) {
        return singleEventRepo.findFutureOwnedByUser(LocalDateTime.now(), userId);
    }

    private Integer validateDaysCount(Integer days) {
        if (days == null) {
            return null;
        } else if (days == 0) {
            return 1;
        } else {
            return days;
        }
    }

    public void changeCounterOfHashtag(Hashtag hashtag, boolean counterIncrease) {
        int counter = hashtag.getCountOfHashtagUsage();
        hashtag.setCountOfHashtagUsage(counterIncrease ? counter++ : counter--);
        hashtagService.saveHashtag(hashtag);
    }

}
