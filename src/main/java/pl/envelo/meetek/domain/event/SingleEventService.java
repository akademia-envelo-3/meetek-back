package pl.envelo.meetek.domain.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.domain.event.model.SingleEventCreateDto;
import pl.envelo.meetek.domain.event.model.SingleEventLongDto;
import pl.envelo.meetek.domain.event.model.SingleEventShortDto;
import pl.envelo.meetek.domain.hashtag.Hashtag;
import pl.envelo.meetek.domain.survey.model.Survey;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.domain.hashtag.HashtagService;
import pl.envelo.meetek.domain.survey.SurveyService;
import pl.envelo.meetek.utils.DtoMapperService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class SingleEventService {

    private final SingleEventRepo eventRepo;
    private final SurveyService surveyService;
    private final HashtagService hashtagService;
    private final SingleEventValidator eventValidator;
    private final DtoMapperService mapperService;

    @Transactional(readOnly = true)
    public SingleEventLongDto getEventById(long id) {
        SingleEvent event = getEventByIdNotDto(id);
        return mapperService.mapToSingleEventLongDto(event);
    }

    @Transactional(readOnly = true)
    public SingleEvent getEventByIdNotDto(long id) {
        SingleEvent event = eventValidator.validateExists(id);
        Set<Survey> surveys = event.getSurveys();
        if (!surveys.isEmpty()) {
            surveys.forEach(surveyService::setSurveyFields);
        }
        return event;
    }

    @Transactional
    public SingleEventShortDto createEvent(StandardUser user, SingleEventCreateDto eventDto) {
        SingleEvent event = mapperService.mapToSingleEvent(eventDto);
        event.setOwner(user);
        eventValidator.validateInput(event);
        event = eventRepo.save(event);
        return mapperService.mapToSingleEventShortDto(event);
    }

    @Transactional
    public void deleteById(long eventId) {
        eventValidator.validateExists(eventId);
        eventRepo.deleteById(eventId);
    }

    public List<SingleEventShortDto> getAllPublicFutureNotAcceptedEvents(long userId, Integer days) {
        Integer validatedDays = eventValidator.validateDaysCount(days);
        if (validatedDays == null) {
            return getAllPublicFutureNotAcceptedEvents(userId);
        } else {
            return getAllPublicFutureNotAcceptedEventsForFewNearestDays(userId, days);
        }
    }

    @Transactional(readOnly = true)
    private List<SingleEventShortDto> getAllPublicFutureNotAcceptedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPublicFutureNotAcceptedByUserAll(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    private List<SingleEventShortDto> getAllPublicFutureNotAcceptedEventsForFewNearestDays(long userId, int days) {
        List<SingleEvent> events = eventRepo.findAllPublicFutureNotAcceptedByUserForFewDays(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(days),
                userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllEventsBeforeToday() {
        List<SingleEvent> events = eventRepo.findAllByDateTimeFromBeforeOrderByDateTimeFromDesc(LocalDateTime.now());
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllEventsAfterToday() {
        List<SingleEvent> events = eventRepo.findAllByDateTimeFromAfterOrderByDateTimeFromAsc(LocalDateTime.now());
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllPublicPastNotAcceptedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPublicPastNotAcceptedByUser(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllPastAcceptedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPastAcceptedByUser(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();

    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> findAllPastOwnedByUser(long userId) {
        List<SingleEvent> events = eventRepo.findPastOwnedByUser(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    public List<SingleEventShortDto> getAllFutureAcceptedEvents(long userId, Integer days) {
        Integer validatedDays = eventValidator.validateDaysCount(days);
        if (validatedDays == null) {
            return getAllFutureAcceptedEvents(userId);
        } else {
            return getAllFutureAcceptedEventsForFewNearestDays(userId, validatedDays);
        }
    }

    @Transactional(readOnly = true)
    private List<SingleEventShortDto> getAllFutureAcceptedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllFutureAccepted(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    private List<SingleEventShortDto> getAllFutureAcceptedEventsForFewNearestDays(long userId, int days) {
        List<SingleEvent> events = eventRepo.findAllFutureAcceptedForFewNearestDays(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(days),
                userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> findAllFutureOwnedByUser(long userId) {
        List<SingleEvent> events = eventRepo.findFutureOwnedByUser(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional
    private void addSurvey(Survey survey) {
        surveyService.createSurvey(survey);
    }

    @Transactional
    public void changeCounterOfHashtag(Hashtag hashtag, boolean counterIncrease) {
        int counter = hashtag.getCountOfHashtagUsage();
        hashtag.setCountOfHashtagUsage(counterIncrease ? ++counter : --counter);
        hashtagService.saveHashtag(hashtag);
    }

}
