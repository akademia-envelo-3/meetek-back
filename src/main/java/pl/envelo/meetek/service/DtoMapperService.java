package pl.envelo.meetek.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.dto.AttachmentDto;
import pl.envelo.meetek.dto.CategoryDto;
import pl.envelo.meetek.dto.CoordinatesDto;
import pl.envelo.meetek.dto.HashtagDto;
import pl.envelo.meetek.dto.comment.EventCommentDto;
import pl.envelo.meetek.dto.comment.RequestCommentDto;
import pl.envelo.meetek.dto.event.*;
import pl.envelo.meetek.dto.group.SectionLongDto;
import pl.envelo.meetek.dto.group.SectionShortDto;
import pl.envelo.meetek.dto.notification.NotificationCategoryDto;
import pl.envelo.meetek.dto.notification.NotificationDto;
import pl.envelo.meetek.dto.notification.NotificationTypeDto;
import pl.envelo.meetek.dto.request.CategoryRequestDto;
import pl.envelo.meetek.dto.request.RequestBoxDto;
import pl.envelo.meetek.dto.request.RequestStatusDto;
import pl.envelo.meetek.dto.survey.SurveyChoiceDto;
import pl.envelo.meetek.dto.survey.SurveyDto;
import pl.envelo.meetek.dto.survey.SurveyResponseDto;
import pl.envelo.meetek.dto.user.AdminDto;
import pl.envelo.meetek.dto.user.GuestDto;
import pl.envelo.meetek.dto.user.StandardUserLongDto;
import pl.envelo.meetek.dto.user.StandardUserShortDto;
import pl.envelo.meetek.model.Attachment;
import pl.envelo.meetek.model.Category;
import pl.envelo.meetek.model.Coordinates;
import pl.envelo.meetek.model.Hashtag;
import pl.envelo.meetek.model.comment.EventComment;
import pl.envelo.meetek.model.comment.RequestComment;
import pl.envelo.meetek.model.event.EventResponse;
import pl.envelo.meetek.model.event.RecurringEventSet;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.model.group.Section;
import pl.envelo.meetek.model.notification.Notification;
import pl.envelo.meetek.model.notification.NotificationCategory;
import pl.envelo.meetek.model.notification.NotificationType;
import pl.envelo.meetek.model.request.CategoryRequest;
import pl.envelo.meetek.model.request.RequestBox;
import pl.envelo.meetek.model.request.RequestStatus;
import pl.envelo.meetek.model.survey.Survey;
import pl.envelo.meetek.model.survey.SurveyChoice;
import pl.envelo.meetek.model.survey.SurveyResponse;
import pl.envelo.meetek.model.user.Admin;
import pl.envelo.meetek.model.user.Guest;
import pl.envelo.meetek.model.user.StandardUser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class DtoMapperService {
    private final ModelMapper modelMapper;

    public HashtagDto mapToHashtagDto(Hashtag hashtag) {
        return modelMapper.map(hashtag, HashtagDto.class);
    }

    public Hashtag mapToHashtag(HashtagDto hashtagDto) {
        return modelMapper.map(hashtagDto, Hashtag.class);
    }

    public CoordinatesDto mapToCoordinatesDto(Coordinates coordinates) {
        return modelMapper.map(coordinates, CoordinatesDto.class);
    }

    public Coordinates mapToCoordinates(CoordinatesDto coordinatesDto) {
        return modelMapper.map(coordinatesDto, Coordinates.class);
    }

    public CategoryDto mapToCategoryDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    public Category mapToCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    public Attachment mapToAttachmentDto(Attachment attachment) {
        return modelMapper.map(attachment, Attachment.class);
    }

    public AttachmentDto mapToAttachment(AttachmentDto attachmentDto) {
        return modelMapper.map(attachmentDto, AttachmentDto.class);
    }

    public AdminDto mapToAdminDto(Admin admin) {
        return modelMapper.map(admin, AdminDto.class);
    }

    public GuestDto mapToGuestDto(Guest guest) {
        return modelMapper.map(guest, GuestDto.class);
    }

    public Guest mapToGuest(GuestDto guestDto) {
        return modelMapper.map(guestDto, Guest.class);
    }

    public StandardUserLongDto maptoStandardUserLongDto(StandardUser standardUser) {
        return modelMapper.map(standardUser, StandardUserLongDto.class);
    }

    public StandardUser mapToStandardUser(StandardUserLongDto standardUserLongDto) {
        return modelMapper.map(standardUserLongDto, StandardUser.class);
    }

    public StandardUserShortDto mapToStandardUserShortDto(StandardUser standardUser) {
        return modelMapper.map(standardUser, StandardUserShortDto.class);
    }

    public StandardUser mapToStandardUser(StandardUserShortDto standardUserShortDto) {
        return modelMapper.map(standardUserShortDto, StandardUser.class);
    }

    public Survey mapToSurvey(SurveyDto surveyDto) {
        return modelMapper.map(surveyDto, Survey.class);
    }

    public SurveyDto mapToSurveyDto(Survey survey) {
        SurveyDto surveyDto = modelMapper.map(survey, SurveyDto.class);
        surveyDto.setChoicePercent(calculatePercentage(createResponseCount(survey)));
        return surveyDto;
    }

    public SurveyChoice mapToSurveyChoice(SurveyChoiceDto surveyChoiceDto) {
        return modelMapper.map(surveyChoiceDto, SurveyChoice.class);
    }

    public SurveyChoiceDto mapToSurveyChoiceDto(SurveyChoice surveyChoice) {
        return modelMapper.map(surveyChoice, SurveyChoiceDto.class);
    }

    public SurveyResponse mapToSurveyResponse(SurveyResponseDto surveyResponseDto) {
        return modelMapper.map(surveyResponseDto, SurveyResponse.class);
    }

    public SurveyResponseDto mapToSurveyResponseDto(SurveyResponse surveyResponse) {
        return modelMapper.map(surveyResponse, SurveyResponseDto.class);
    }

    public CategoryRequest mapToCategoryRequest(CategoryRequestDto categoryRequestDto) {
        return modelMapper.map(categoryRequestDto, CategoryRequest.class);
    }

    public CategoryRequestDto mapToCategoryRequestDto(CategoryRequest categoryRequest) {
        return modelMapper.map(categoryRequest, CategoryRequestDto.class);
    }

    public RequestBox mapToRequestBox(RequestBoxDto requestBoxDto) {
        return modelMapper.map(requestBoxDto, RequestBox.class);
    }

    public RequestBoxDto mapToRequestBoxDto(RequestBox requestBox) {
        return modelMapper.map(requestBox, RequestBoxDto.class);
    }

    public RequestStatus mapToRequestStatus(RequestStatusDto requestStatusDto) {
        return modelMapper.map(requestStatusDto, RequestStatus.class);
    }

    public RequestStatusDto mapToRequestStatusDto(RequestStatus requestStatus) {
        return modelMapper.map(requestStatus, RequestStatusDto.class);
    }

    public Notification mapToNotification(NotificationDto notificationDto) {
        return modelMapper.map(notificationDto, Notification.class);
    }

    public NotificationDto mapToNotificationDto(Notification notification) {
        return modelMapper.map(notification, NotificationDto.class);
    }

    public NotificationType mapToNotificationType(NotificationTypeDto notificationTypeDto) {
        return modelMapper.map(notificationTypeDto, NotificationType.class);
    }

    public NotificationTypeDto mapToNotificationTypeDto(NotificationType notificationType) {
        return modelMapper.map(notificationType, NotificationTypeDto.class);
    }

    public NotificationCategory mapToNotificationCategory(NotificationCategoryDto notificationCategoryDto) {
        return modelMapper.map(notificationCategoryDto, NotificationCategory.class);
    }

    public NotificationCategoryDto mapToNotificationCategoryDto(NotificationCategory notificationCategory) {
        return modelMapper.map(notificationCategory, NotificationCategoryDto.class);
    }

    public Section mapToSection(SectionLongDto sectionLongDto) {
        return modelMapper.map(sectionLongDto, Section.class);
    }

    public Section mapToSection(SectionShortDto sectionShortDto) {
        return modelMapper.map(sectionShortDto, Section.class);
    }

    public SectionLongDto mapToSectionLongDto(Section section) {
        return modelMapper.map(section, SectionLongDto.class);
    }

    public SectionShortDto mapToSectionShortDto(Section section) {
        return modelMapper.map(section, SectionShortDto.class);
    }

    public EventResponse mapToEventResponse(EventResponseDto eventResponseDto) {
        return modelMapper.map(eventResponseDto, EventResponse.class);
    }

    public EventResponseDto mapToEventResponseDto(EventResponse eventResponse) {
        return modelMapper.map(eventResponse, EventResponseDto.class);
    }

    public RecurringEventSet mapToRecurringEventSet(RecurringEventSetDto recurringEventSetDto) {
        return modelMapper.map(recurringEventSetDto, RecurringEventSet.class);
    }

    public RecurringEventSetDto mapToRecurringEventSetDto(RecurringEventSet recurringEventSet) {
        return modelMapper.map(recurringEventSet, RecurringEventSetDto.class);
    }

    public SingleEvent mapToSingleEvent(SingleEventLongDto singleEventDto) {
        return modelMapper.map(singleEventDto, SingleEvent.class);
    }

    public SingleEvent mapToSingleEvent(SingleEventShortDto singleEventDto) {
        return modelMapper.map(singleEventDto, SingleEvent.class);
    }

    public SingleEventLongDto mapToSingleEventLongDto(SingleEvent singleEvent) {
        return modelMapper.map(singleEvent, SingleEventLongDto.class);
    }

    public SingleEventShortDto mapToSingleEventShortDto(SingleEvent singleEvent) {
        return modelMapper.map(singleEvent, SingleEventShortDto.class);
    }

    public SingleEventGuestDto mapToSingleEventGuestDto(SingleEvent singleEvent) {
        return modelMapper.map(singleEvent, SingleEventGuestDto.class);
    }

    public EventComment mapToEventComment(EventCommentDto eventCommentDto) {
        return modelMapper.map(eventCommentDto, EventComment.class);
    }

    public EventCommentDto mapToEventCommentDto(EventComment eventComment) {
        return modelMapper.map(eventComment, EventCommentDto.class);
    }

    public RequestComment mapToRequestComment(RequestCommentDto requestCommentDto) {
        return modelMapper.map(requestCommentDto, RequestComment.class);
    }

    private Map<Long, Integer> createResponseCount(Survey survey) {

        Map<Long, Integer> responseCount = new HashMap<>();
        for (SurveyChoice surveyChoice : survey.getChoices()) {
            responseCount.put(surveyChoice.getChoiceId(), 0);
        }
        for(SurveyResponse surveyResponse:survey.getResponses()){
            for(SurveyChoice answer :surveyResponse.getAnswers()){
                responseCount.put(answer.getChoiceId(),responseCount.get(answer.getChoiceId()) + 1);
            }
        }
        return responseCount;
    }
    private Map<Long, BigDecimal> calculatePercentage(Map<Long, Integer> responseCount){

        double value = 0;
        Map<Long, BigDecimal> choicePercent = new HashMap<>();
        for(Long key : responseCount.keySet()){
            value = value + responseCount.get(key);
        }
        for(Long key : responseCount.keySet()){
            choicePercent.put(key, BigDecimal.valueOf(responseCount.get(key)/value*100).setScale(2, RoundingMode.HALF_DOWN));
        }
        return choicePercent;
    }

}
