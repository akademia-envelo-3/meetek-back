package pl.envelo.meetek.domain.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.event.model.*;
import pl.envelo.meetek.domain.hashtag.Hashtag;
import pl.envelo.meetek.domain.hashtag.HashtagService;
import pl.envelo.meetek.domain.survey.SurveyService;
import pl.envelo.meetek.domain.survey.model.Survey;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.utils.DtoMapperService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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
        addHashtags(event);
        addInvitedUsers(event);
        eventValidator.validateCategory(event);
        addCoordinates(event);
        addAttachments(event);
        event.setSurveys(null);
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

    private void addInvitedUsers(SingleEvent event) {
        Set<StandardUser> users = new HashSet<>();
        if (event.getInvitedUsers() != null) {
            for (StandardUser user : event.getInvitedUsers()) {
                if (!user.getParticipantId().equals(event.getOwner().getParticipantId()) && !users.contains(user)) {
                    users.add(eventValidator.validateUser(user.getParticipantId()));
                }
            }
            event.setInvitedUsers(users);
        }
    }

    //TODO
    private void addCoordinates(SingleEvent event) {
    }

    //TODO
    private void addAttachments(SingleEvent event) {
    }


    private void addHashtags(SingleEvent event) {

        Set<Hashtag> foundHashtags = hashtagService.findAllHashtags(event.getName() + " " + event.getDescription());
        Set<Hashtag> updatedHashtags = hashtagService.checkHashtagSet(null, event.getHashtags());
        hashtagService.checkHashtagSet(updatedHashtags, foundHashtags);
        event.setHashtags(updatedHashtags);

    }

}
