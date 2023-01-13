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

@AllArgsConstructor
@Service
public class DtoMapperService {
    private final ModelMapper modelMapper = new ModelMapper();

    private HashtagDto mapToHashtagDto(Hashtag hashtag) {
        return modelMapper.map(hashtag, HashtagDto.class);
    }

    private Hashtag mapToHashtag(HashtagDto hashtagDto) {
        return modelMapper.map(hashtagDto, Hashtag.class);
    }

    private CoordinatesDto mapToCoordinatesDto(Coordinates coordinates) {
        return modelMapper.map(coordinates, CoordinatesDto.class);
    }

    private Coordinates mapToCoordinates(CoordinatesDto coordinatesDto) {
        return modelMapper.map(coordinatesDto, Coordinates.class);
    }

    private CategoryDto mapToCategoryDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    private Category mapToCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    private Attachment mapToAttachmentDto(Attachment attachment) {
        return modelMapper.map(attachment, Attachment.class);
    }

    private AttachmentDto mapToAttachment(AttachmentDto attachmentDto) {
        return modelMapper.map(attachmentDto, AttachmentDto.class);
    }

    private AdminDto mapToAdminDto(Admin admin) {
        return modelMapper.map(admin, AdminDto.class);
    }

    private GuestDto mapToGuestDto(Guest guest) {
        return modelMapper.map(guest, GuestDto.class);
    }

    private Guest mapToGuest(GuestDto guestDto) {
        return modelMapper.map(guestDto, Guest.class);
    }

    private StandardUserLongDto maptoStandardUserLongDto(StandardUser standardUser) {
        return modelMapper.map(standardUser, StandardUserLongDto.class);
    }

    private StandardUser mapToStandardUser(StandardUserLongDto standardUserLongDto) {
        return modelMapper.map(standardUserLongDto, StandardUser.class);
    }

    private StandardUserShortDto mapToStandardUserShortDto(StandardUser standardUser) {
        return modelMapper.map(standardUser, StandardUserShortDto.class);
    }

    private StandardUser mapToStandardUser(StandardUserShortDto standardUserShortDto) {
        return modelMapper.map(standardUserShortDto, StandardUser.class);
    }

    private Survey mapToSurvey(SurveyDto surveyDto) {
        return modelMapper.map(surveyDto, Survey.class);
    }

    private SurveyDto mapToSurveyDto(Survey survey) {
        return modelMapper.map(survey, SurveyDto.class);
    }

    private SurveyChoice mapToSurveyChoice(SurveyChoiceDto surveyChoiceDto) {
        return modelMapper.map(surveyChoiceDto, SurveyChoice.class);
    }

    private SurveyChoiceDto mapToSurveyChoiceDto(SurveyChoice surveyChoice) {
        return modelMapper.map(surveyChoice, SurveyChoiceDto.class);
    }

    private SurveyResponse mapToSurveyResponse(SurveyResponseDto surveyResponseDto) {
        return modelMapper.map(surveyResponseDto, SurveyResponse.class);
    }

    private SurveyResponseDto mapToSurveyResponseDto(SurveyResponse surveyResponse) {
        return modelMapper.map(surveyResponse, SurveyResponseDto.class);
    }

    private CategoryRequest mapToCategoryRequest(CategoryRequestDto categoryRequestDto) {
        return modelMapper.map(categoryRequestDto, CategoryRequest.class);
    }

    private CategoryRequestDto mapToCategoryRequestDto(CategoryRequest categoryRequest) {
        return modelMapper.map(categoryRequest, CategoryRequestDto.class);
    }

    private RequestBox mapToRequestBox(RequestBoxDto requestBoxDto) {
        return modelMapper.map(requestBoxDto, RequestBox.class);
    }

    private RequestBoxDto mapToRequestBoxDto(RequestBox requestBox) {
        return modelMapper.map(requestBox, RequestBoxDto.class);
    }

    private RequestStatus mapToRequestStatus(RequestStatusDto requestStatusDto) {
        return modelMapper.map(requestStatusDto, RequestStatus.class);
    }

    private RequestStatusDto mapToRequestStatusDto(RequestStatus requestStatus) {
        return modelMapper.map(requestStatus, RequestStatusDto.class);
    }

    private Notification mapToNotification(NotificationDto notificationDto) {
        return modelMapper.map(notificationDto, Notification.class);
    }

    private NotificationDto mapToNotificationDto(Notification notification) {
        return modelMapper.map(notification, NotificationDto.class);
    }

    private NotificationType mapToNotificationType(NotificationTypeDto notificationTypeDto) {
        return modelMapper.map(notificationTypeDto, NotificationType.class);
    }

    private NotificationTypeDto mapToNotificationTypeDto(NotificationType notificationType) {
        return modelMapper.map(notificationType, NotificationTypeDto.class);
    }

    private NotificationCategory mapToNotificationCategory(NotificationCategoryDto notificationCategoryDto) {
        return modelMapper.map(notificationCategoryDto, NotificationCategory.class);
    }

    private NotificationCategoryDto mapToNotificationCategoryDto(NotificationCategory notificationCategory) {
        return modelMapper.map(notificationCategory, NotificationCategoryDto.class);
    }

    private Section mapToSection(SectionLongDto sectionLongDto) {
        return modelMapper.map(sectionLongDto, Section.class);
    }

    private Section mapToSection(SectionShortDto sectionShortDto) {
        return modelMapper.map(sectionShortDto, Section.class);
    }

    private SectionLongDto mapToSectionLongDto(Section section) {
        return modelMapper.map(section, SectionLongDto.class);
    }

    private SectionShortDto mapToSectionShortDto(Section section) {
        return modelMapper.map(section, SectionShortDto.class);
    }

    private EventResponse mapToEventResponse(EventResponseDto eventResponseDto) {
        return modelMapper.map(eventResponseDto, EventResponse.class);
    }

    private EventResponseDto mapToEventResponseDto(EventResponse eventResponse) {
        return modelMapper.map(eventResponse, EventResponseDto.class);
    }

    private RecurringEventSet mapToRecurringEventSet(RecurringEventSetDto recurringEventSetDto) {
        return modelMapper.map(recurringEventSetDto, RecurringEventSet.class);
    }

    private RecurringEventSetDto mapToRecurringEventSetDto(RecurringEventSet recurringEventSet) {
        return modelMapper.map(recurringEventSet, RecurringEventSetDto.class);
    }

    private SingleEvent mapToSingleEvent(SingleEventLongDto singleEventDto) {
        return modelMapper.map(singleEventDto, SingleEvent.class);
    }

    private SingleEvent mapToSingleEvent(SingleEventShortDto singleEventDto) {
        return modelMapper.map(singleEventDto, SingleEvent.class);
    }

    private SingleEventLongDto mapToSingleEventLongDto(SingleEvent singleEvent) {
        return modelMapper.map(singleEvent, SingleEventLongDto.class);
    }

    private SingleEventShortDto mapToSingleEventShortDto(SingleEvent singleEvent) {
        return modelMapper.map(singleEvent, SingleEventShortDto.class);
    }

    private SingleEventGuestDto mapToSingleEventGuestDto(SingleEvent singleEvent) {
        return modelMapper.map(singleEvent, SingleEventGuestDto.class);
    }

    private EventComment mapToEventComment(EventCommentDto eventCommentDto) {
        return modelMapper.map(eventCommentDto, EventComment.class);
    }

    private EventCommentDto mapToEventCommentDto(EventComment eventComment) {
        return modelMapper.map(eventComment, EventCommentDto.class);
    }

    private RequestComment mapToRequestComment(RequestCommentDto requestCommentDto) {
        return modelMapper.map(requestCommentDto, RequestComment.class);
    }

}
