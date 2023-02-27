package pl.envelo.meetek.utils;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.attachment.Attachment;
import pl.envelo.meetek.domain.attachment.AttachmentDto;
import pl.envelo.meetek.domain.category.Category;
import pl.envelo.meetek.domain.category.CategoryDto;
import pl.envelo.meetek.domain.comment.model.*;
import pl.envelo.meetek.domain.coordinates.Coordinates;
import pl.envelo.meetek.domain.coordinates.CoordinatesDto;
import pl.envelo.meetek.domain.event.model.*;
import pl.envelo.meetek.domain.group.model.Section;
import pl.envelo.meetek.domain.group.model.SectionCreateDto;
import pl.envelo.meetek.domain.group.model.SectionLongDto;
import pl.envelo.meetek.domain.group.model.SectionShortDto;
import pl.envelo.meetek.domain.hashtag.Hashtag;
import pl.envelo.meetek.domain.hashtag.HashtagCreateDto;
import pl.envelo.meetek.domain.hashtag.HashtagDto;
import pl.envelo.meetek.domain.notification.model.*;
import pl.envelo.meetek.domain.request.model.CategoryRequest;
import pl.envelo.meetek.domain.request.model.CategoryRequestCreateDto;
import pl.envelo.meetek.domain.request.model.CategoryRequestDto;
import pl.envelo.meetek.domain.request.model.RequestStatus;
import pl.envelo.meetek.domain.survey.model.*;
import pl.envelo.meetek.domain.user.model.*;

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

    public HashtagCreateDto mapToHashtagCreateDto(Hashtag hashtag) {
        return modelMapper.map(hashtag, HashtagCreateDto.class);
    }

    public Hashtag mapToHashtag(HashtagCreateDto hashtagCreateDto) {
        return modelMapper.map(hashtagCreateDto, Hashtag.class);
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

    public AttachmentDto mapToAttachmentDto(Attachment attachment) {
        return modelMapper.map(attachment, AttachmentDto.class);
    }

    public Attachment mapToAttachment(AttachmentDto attachmentDto) {
        return modelMapper.map(attachmentDto, Attachment.class);
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


    public StandardUserDto mapToStandardUserShortDto(StandardUser standardUser) {
        return modelMapper.map(standardUser, StandardUserDto.class);
    }

    public StandardUser mapToStandardUser(StandardUserDto standardUserDto) {
        return modelMapper.map(standardUserDto, StandardUser.class);
    }

    public Survey mapToSurvey(SurveyDto surveyDto) {
        return modelMapper.map(surveyDto, Survey.class);
    }

    public SurveyCreateDto mapToSurveyCreateDto(Survey survey) {
        return modelMapper.map(survey, SurveyCreateDto.class);
    }

    public SurveyDto mapToSurveyDto(Survey survey) {
        return modelMapper.map(survey, SurveyDto.class);
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

    public SurveyResponse mapToSurveyResponseCreate(SurveyResponseCreateDto surveyResponseCreateDto) {
        return modelMapper.map(surveyResponseCreateDto, SurveyResponse.class);
    }

    public SurveyResponseDto mapToSurveyResponseDto(SurveyResponse surveyResponse) {
        return modelMapper.map(surveyResponse, SurveyResponseDto.class);
    }

    public CategoryRequest mapToCategoryRequest(CategoryRequestDto categoryRequestDto) {
        CategoryRequest request = modelMapper.map(categoryRequestDto, CategoryRequest.class);
        request.setStatus(RequestStatus.valueOf(categoryRequestDto.getRequestStatus().toUpperCase()));
        return request;
    }

    public CategoryRequest mapToCategoryRequestCreate(CategoryRequestCreateDto categoryRequestCreateDto) {
        return modelMapper.map(categoryRequestCreateDto, CategoryRequest.class);
    }

    public CategoryRequestDto mapToCategoryRequestDto(CategoryRequest categoryRequest) {
        CategoryRequestDto requestDto = modelMapper.map(categoryRequest, CategoryRequestDto.class);
        requestDto.setRequestStatus(categoryRequest.getStatus().toString());
        return requestDto;
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

    public Section mapToSection(SectionCreateDto sectionCreateDto) {
        return modelMapper.map(sectionCreateDto, Section.class);
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

    public RecurringEventSet mapToRecurringEventSet(RecurringEventSetCreateDto recurringEventSetCreateDto) {
        return modelMapper.map(recurringEventSetCreateDto, RecurringEventSet.class);
    }

    public SingleEvent mapToSingleEvent(RecurringEventSetCreateDto eventSetCreateDto) {
        return modelMapper.map(eventSetCreateDto, SingleEvent.class);
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
    public SingleEventCreateDto mapToSingleEventCreateDto(SingleEvent singleEvent) {
        return modelMapper.map(singleEvent, SingleEventCreateDto.class);
    }


    public SingleEvent mapToSingleEvent(SingleEventCreateDto singleEventCreateDto) {
        return modelMapper.map(singleEventCreateDto, SingleEvent.class);
    }

    public EventComment mapToEventComment(EventCommentDto eventCommentDto) {
        return modelMapper.map(eventCommentDto, EventComment.class);
    }

    public EventCommentDto mapToEventCommentDto(EventComment eventComment) {
        return modelMapper.map(eventComment, EventCommentDto.class);
    }

    public EventComment mapToEventComment(EventCommentCreateDto eventCommentCreateDto) {
        return modelMapper.map(eventCommentCreateDto, EventComment.class);
    }

    public RequestComment mapToRequestComment(RequestCommentDto requestCommentDto) {
        return modelMapper.map(requestCommentDto, RequestComment.class);
    }

    public RequestCommentDto mapToRequestCommentDto(RequestComment requestComment) {
        return modelMapper.map(requestComment, RequestCommentDto.class);
    }

    public RequestComment mapToRequestComment(RequestCommentCreateDto requestCommentCreateDto) {
        return modelMapper.map(requestCommentCreateDto, RequestComment.class);
    }
}
