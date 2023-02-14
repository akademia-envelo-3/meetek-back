package pl.envelo.meetek.domain.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.domain.event.model.SingleEventCreateDto;
import pl.envelo.meetek.domain.event.model.SingleEventLongDto;
import pl.envelo.meetek.domain.event.model.SingleEventShortDto;
import pl.envelo.meetek.domain.hashtag.HashtagService;
import pl.envelo.meetek.domain.survey.SurveyService;
import pl.envelo.meetek.domain.survey.model.Survey;
import pl.envelo.meetek.domain.user.model.StandardUser;
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

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllPublicFutureNotRespondedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPublicFutureNotRespondedByUser(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllPublicPastNotRespondedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPublicPastNotRespondedByUser(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllFutureAcceptedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllFutureWithResponseByUser(LocalDateTime.now(), userId, 1);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllPastAcceptedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPastWithResponseByUser(LocalDateTime.now(), userId, 1);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllFutureRejectedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllFutureWithResponseByUser(LocalDateTime.now(), userId, 2);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllPastRejectedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPastWithResponseByUser(LocalDateTime.now(), userId, 2);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllFutureUndecidedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllFutureWithResponseByUser(LocalDateTime.now(), userId, 3);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllPastUndecidedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPastWithResponseByUser(LocalDateTime.now(), userId, 3);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> findAllFutureOwnedByUser(long userId) {
        List<SingleEvent> events = eventRepo.findFutureOwnedByUser(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> findAllPastOwnedByUser(long userId) {
        List<SingleEvent> events = eventRepo.findPastOwnedByUser(LocalDateTime.now(), userId);
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

    @Transactional
    public void setEventOwnerByAdmin(long newOwnerId, long eventId) {
        SingleEvent singleEvent = eventValidator.validateExists(eventId);
        StandardUser newOwner = eventValidator.validateOwnerForAdmin(singleEvent, newOwnerId);
        eventRepo.updateOwner(singleEvent.getEventId(), newOwner.getParticipantId());
    }

}
