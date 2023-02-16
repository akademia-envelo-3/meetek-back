package pl.envelo.meetek.domain.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.event.model.*;
import pl.envelo.meetek.domain.hashtag.HashtagService;
import pl.envelo.meetek.domain.survey.SurveyService;
import pl.envelo.meetek.domain.survey.model.Survey;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.utils.DtoMapperService;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<SingleEventShortDto> getAllPublicNotRespondedEvents(long userId, String time) {
        TimeStatus timeStatus = eventValidator.validateTimeParameter(time);
        if (timeStatus == TimeStatus.FUTURE) {
            return getAllPublicFutureNotRespondedEvents(userId);
        } else if (timeStatus == TimeStatus.PAST) {
            return getAllPublicPastNotRespondedEvents(userId);
        }
        return new ArrayList<>();
    }

    private List<SingleEventShortDto> getAllPublicFutureNotRespondedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPublicFutureNotRespondedByUser(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    private List<SingleEventShortDto> getAllPublicPastNotRespondedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPublicPastNotRespondedByUser(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllRespondedEvents(long userId, String time, String response) {
        TimeStatus timeStatus = eventValidator.validateTimeParameter(time);
        EventResponseStatus eventResponseStatus = eventValidator.validateResponseParameter(response);
        switch (eventResponseStatus) {
            case ACCEPTED -> {
                if (timeStatus == TimeStatus.FUTURE) {
                    return getAllFutureAcceptedEvents(userId);
                } else if (timeStatus == TimeStatus.PAST) {
                    return getAllPastAcceptedEvents(userId);
                }
            }
            case REJECTED -> {
                if (timeStatus == TimeStatus.FUTURE) {
                    return getAllFutureRejectedEvents(userId);
                } else if (timeStatus == TimeStatus.PAST) {
                    return getAllPastRejectedEvents(userId);
                }
            }
            case UNDECIDED -> {
                if (timeStatus == TimeStatus.FUTURE) {
                    return getAllFutureUndecidedEvents(userId);
                } else if (timeStatus == TimeStatus.PAST) {
                    return getAllPastUndecidedEvents(userId);
                }
            }
        }
        return new ArrayList<>();
    }

    private List<SingleEventShortDto> getAllFutureAcceptedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllFutureWithResponseByUser(LocalDateTime.now(), userId, EventResponseStatus.ACCEPTED.toString());
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    private List<SingleEventShortDto> getAllPastAcceptedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPastWithResponseByUser(LocalDateTime.now(), userId, EventResponseStatus.ACCEPTED.toString());
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    private List<SingleEventShortDto> getAllFutureRejectedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllFutureWithResponseByUser(LocalDateTime.now(), userId, EventResponseStatus.REJECTED.toString());
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    private List<SingleEventShortDto> getAllPastRejectedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPastWithResponseByUser(LocalDateTime.now(), userId, EventResponseStatus.REJECTED.toString());
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    private List<SingleEventShortDto> getAllFutureUndecidedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllFutureWithResponseByUser(LocalDateTime.now(), userId, EventResponseStatus.UNDECIDED.toString());
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    private List<SingleEventShortDto> getAllPastUndecidedEvents(long userId) {
        List<SingleEvent> events = eventRepo.findAllPastWithResponseByUser(LocalDateTime.now(), userId, EventResponseStatus.UNDECIDED.toString());
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllOwnedByUser(long userId, String time) {
        TimeStatus timeStatus = eventValidator.validateTimeParameter(time);
        if (timeStatus == TimeStatus.FUTURE) {
            return getAllFutureOwnedByUser(userId);
        } else if (timeStatus == TimeStatus.PAST) {
            return getAllPastOwnedByUser(userId);
        }
        return new ArrayList<>();
    }

    private List<SingleEventShortDto> getAllFutureOwnedByUser(long userId) {
        List<SingleEvent> events = eventRepo.findFutureOwnedByUser(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    private List<SingleEventShortDto> getAllPastOwnedByUser(long userId) {
        List<SingleEvent> events = eventRepo.findPastOwnedByUser(LocalDateTime.now(), userId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleEventShortDto> getAllEvents(String time) {
        TimeStatus timeStatus = eventValidator.validateTimeParameter(time);
        if (timeStatus == TimeStatus.FUTURE) {
            return getAllPastEvents();
        } else if (timeStatus == TimeStatus.PAST) {
            return getAllFutureEvents();
        }
        return new ArrayList<>();
    }

    private List<SingleEventShortDto> getAllFutureEvents() {
        List<SingleEvent> events = eventRepo.findAllByDateTimeFromBeforeOrderByDateTimeFromDesc(LocalDateTime.now());
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    private List<SingleEventShortDto> getAllPastEvents() {
        List<SingleEvent> events = eventRepo.findAllByDateTimeFromAfterOrderByDateTimeFromAsc(LocalDateTime.now());
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    @Transactional
    public void setEventOwnerByAdmin(long newOwnerId, long eventId) {
        SingleEvent singleEvent = eventValidator.validateExists(eventId);
        StandardUser newOwner = eventValidator.validateOwnerForAdmin(singleEvent, newOwnerId);
        eventRepo.updateOwner(singleEvent.getEventId(), newOwner.getParticipantId());
    }

    @Transactional
    public List<SingleEventShortDto> getAllEventsFromSection(long sectionId, String time) {
        TimeStatus timeStatus = eventValidator.validateTimeParameter(time);
        if (timeStatus == TimeStatus.FUTURE) {
            return getAllFutureEventsFromSection(sectionId);
        } else if (timeStatus == TimeStatus.PAST) {
            return getAllPastEventsFromSection(sectionId);
        }
        return new ArrayList<>();
    }

    private List<SingleEventShortDto> getAllPastEventsFromSection(long sectionId) {
        List<SingleEvent> events = eventRepo.findAllPastEventsFromSection(LocalDateTime.now(), sectionId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }

    private List<SingleEventShortDto> getAllFutureEventsFromSection(long sectionId) {
        List<SingleEvent> events = eventRepo.findAllFutureEventsFromSection(LocalDateTime.now(), sectionId);
        return events.stream().map(mapperService::mapToSingleEventShortDto).toList();
    }


}
