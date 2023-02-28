package pl.envelo.meetek.domain.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
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
import pl.envelo.meetek.domain.notification.model.NotificationCategory;
import pl.envelo.meetek.domain.notification.model.NotificationCategoryDto;
import pl.envelo.meetek.domain.notification.model.NotificationType;
import pl.envelo.meetek.domain.notification.model.NotificationTypeDto;
import pl.envelo.meetek.domain.request.model.CategoryRequest;
import pl.envelo.meetek.domain.request.model.CategoryRequestCreateDto;
import pl.envelo.meetek.domain.request.model.CategoryRequestDto;
import pl.envelo.meetek.domain.request.model.RequestStatus;
import pl.envelo.meetek.domain.survey.model.*;
import pl.envelo.meetek.domain.user.model.*;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DtoMapperServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DtoMapperService dtoMapperService;

    private Hashtag hashtag;
    private HashtagDto hashtagDto;

    //Hashtags
    @Test
    public void testMapToHashtagDto() {
        hashtag = new Hashtag();
        hashtag.setName("test");

        hashtagDto = new HashtagDto();
        hashtagDto.setName("test");

        when(modelMapper.map(hashtag, HashtagDto.class)).thenReturn(hashtagDto);

        HashtagDto result = dtoMapperService.mapToHashtagDto(hashtag);

        assertEquals(result.getName(), hashtagDto.getName());

        verify(modelMapper, times(1)).map(hashtag, HashtagDto.class);
    }
    public Hashtag mapToHashtag(HashtagCreateDto hashtagCreateDto) {
        return modelMapper.map(hashtagCreateDto, Hashtag.class);
    }
    @Test
    public void testMapToCreateHashtagDto1() {
        Hashtag hashtag = new Hashtag("example");
        HashtagCreateDto expectedCreateDto = new HashtagCreateDto("example");
        when(modelMapper.map(expectedCreateDto, Hashtag.class)).thenReturn(hashtag);

        Hashtag actualCreateDto = dtoMapperService.mapToHashtag(expectedCreateDto);

        assertEquals(hashtag, actualCreateDto);
    }
    @Test
    public void testMapToHashtag() {
        hashtag = new Hashtag();
        hashtag.setName("test");

        hashtagDto = new HashtagDto();
        hashtagDto.setName("test");

        when(modelMapper.map(hashtagDto, Hashtag.class)).thenReturn(hashtag);

        Hashtag result = dtoMapperService.mapToHashtag(hashtagDto);

        assertEquals(result.getName(), hashtag.getName());

        verify(modelMapper, times(1)).map(hashtagDto, Hashtag.class);
    }

    @Test
    public void testMapToCreateHashtagDto() {
        Hashtag hashtag = new Hashtag("example");
        HashtagCreateDto expectedCreateDto = new HashtagCreateDto("example");
        when(modelMapper.map(hashtag, HashtagCreateDto.class)).thenReturn(expectedCreateDto);

        HashtagCreateDto actualCreateDto = dtoMapperService.mapToHashtagCreateDto(hashtag);

        assertEquals(expectedCreateDto, actualCreateDto);
    }

    //Coordinates
    @Test
    void testMapToCoordinates() {
        CoordinatesDto dto = new CoordinatesDto();
        dto.setLatitude(12.34);
        dto.setLongitude(56.78);
        Coordinates expected = new Coordinates(12.34, 56.78);

        when(modelMapper.map(dto, Coordinates.class)).thenReturn(expected);

        Coordinates actual = dtoMapperService.mapToCoordinates(dto);

        assertEquals(expected.getLatitude(), actual.getLatitude(), 0.001);
        assertEquals(expected.getLongitude(), actual.getLongitude(), 0.001);
    }

    @Test
    public void testMapToCoordinatesDto() {
        Coordinates coordinates = new Coordinates(123.456, 789.012);
        CoordinatesDto expectedDto = new CoordinatesDto(123.456, 789.012);
        when(modelMapper.map(coordinates, CoordinatesDto.class)).thenReturn(expectedDto);

        CoordinatesDto actualDto = dtoMapperService.mapToCoordinatesDto(coordinates);

        assertEquals(expectedDto, actualDto);
    }

    //Category
    @Test
    public void testMapToCategoryDto() {
        Category category = new Category(1L, "example");
        CategoryDto expectedDto = new CategoryDto(1L, "example");
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(expectedDto);

        CategoryDto actualDto = dtoMapperService.mapToCategoryDto(category);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    public void testMapToCategory() {
        CategoryDto categoryDto = new CategoryDto(1L, "example");
        Category expectedCategory = new Category(1L, "example");
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(expectedCategory);

        Category actualCategory = dtoMapperService.mapToCategory(categoryDto);

        assertEquals(expectedCategory, actualCategory);
    }

    //Attachments
    @Test
    public void testMapToAttachmentDto() {
        Attachment attachment = new Attachment();
        attachment.setAttachmentId(1L);
        attachment.setLink("text/plain");

        AttachmentDto attachmentExpected = new AttachmentDto();
        attachmentExpected.setAttachmentId(1L);
        attachmentExpected.setLink("text/plain");

        when(modelMapper.map(attachment, AttachmentDto.class)).thenReturn(attachmentExpected);

        AttachmentDto attachmentDto = dtoMapperService.mapToAttachmentDto(attachment);

        assertEquals(attachment.getAttachmentId(), attachmentDto.getAttachmentId());
        assertEquals(attachment.getLink(), attachmentDto.getLink());
    }

    @Test
    public void testMapToAttachment() {
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setAttachmentId(1L);
        attachmentDto.setLink("text/plain");

        Attachment attachment = new Attachment();
        attachment.setAttachmentId(1L);
        attachment.setLink("text/plain");

        when(modelMapper.map(attachmentDto, Attachment.class)).thenReturn(attachment);

        Attachment attachmentExp = dtoMapperService.mapToAttachment(attachmentDto);

        assertEquals(attachmentDto.getAttachmentId(), attachmentExp.getAttachmentId());
        assertEquals(attachmentDto.getLink(), attachmentExp.getLink());

    }

    //Users
    @Test
    public void testMapToAdminDto() {
        Admin admin = new Admin(1L);
        AdminDto adminDto = new AdminDto(1L);

        when(modelMapper.map(admin, AdminDto.class)).thenReturn(adminDto);

        AdminDto actualDto = dtoMapperService.mapToAdminDto(admin);

        assertEquals(adminDto, actualDto);
    }


    @Test
    public void testMapToStandardUserDto() {
        StandardUser standardUser = new StandardUser(1L);
        StandardUserDto standardUserDto = new StandardUserDto(1L);

        when(modelMapper.map(standardUser, StandardUserDto.class)).thenReturn(standardUserDto);

        StandardUserDto actualDto = dtoMapperService.mapToStandardUserShortDto(standardUser);

        assertEquals(standardUserDto, actualDto);
    }

    @Test
    public void testMapToStandardUser() {
        StandardUser standardUser = new StandardUser(1L);
        StandardUserDto standardUserDto = new StandardUserDto(1L);

        when(modelMapper.map(standardUserDto, StandardUser.class)).thenReturn(standardUser);

        StandardUser actualStandardUser = dtoMapperService.mapToStandardUser(standardUserDto);

        assertEquals(standardUser, actualStandardUser);
    }

    @Test
    public void testMapToGuestDto() {
        Guest guest = new Guest(1L, "Arek", "Morlinek", "ddd@ddd.dd");
        GuestDto guestDto = new GuestDto(1L, "Arek", "Morlinek", "ddd@ddd.dd");

        when(modelMapper.map(guest, GuestDto.class)).thenReturn(guestDto);

        GuestDto actualDto = dtoMapperService.mapToGuestDto(guest);

        assertEquals(guestDto, actualDto);
    }

    @Test
    public void testMapToGuest() {
        GuestDto guestDto = new GuestDto(1L, "Arek", "Morlinek", "ddd@ddd.dd");
        Guest guest = new Guest(1L, "Arek", "Morlinek", "ddd@ddd.dd");

        when(modelMapper.map(guestDto, Guest.class)).thenReturn(guest);

        Guest actualGuest = dtoMapperService.mapToGuest(guestDto);

        assertEquals(guest, actualGuest);
    }

    //Surveys
    @Test
    public void testMapToSurveyDto() {
        Survey survey = new Survey(1L, "Questions");
        SurveyDto surveyDto = new SurveyDto(1L, "Questions");

        when(modelMapper.map(survey, SurveyDto.class)).thenReturn(surveyDto);

        SurveyDto actualDto = dtoMapperService.mapToSurveyDto(survey);

        assertEquals(surveyDto, actualDto);
    }

    @Test
    public void testMapToSurvey() {
        Survey survey = new Survey(1L, "Questions");
        SurveyDto surveyDto = new SurveyDto(1L, "Questions");

        when(modelMapper.map(surveyDto, Survey.class)).thenReturn(survey);

        Survey actualSurvey = dtoMapperService.mapToSurvey(surveyDto);

        assertEquals(survey, actualSurvey);
    }

    @Test
    public void testMapToCreateSurveyDto() {
        Survey survey = new Survey("Question1");
        SurveyCreateDto surveyCreateDto = new SurveyCreateDto("Questions");

        when(modelMapper.map(survey, SurveyCreateDto.class)).thenReturn(surveyCreateDto);

        SurveyCreateDto actualCreateDto = dtoMapperService.mapToSurveyCreateDto(survey);


        assertEquals(surveyCreateDto, actualCreateDto);
    }

    @Test
    public void testMapToSurveyChoiceDto() {
        SurveyChoice surveyChoice = new SurveyChoice(1L, "Choice1");
        SurveyChoiceDto surveyChoiceDto = new SurveyChoiceDto(1L, "Questions");

        when(modelMapper.map(surveyChoice, SurveyChoiceDto.class)).thenReturn(surveyChoiceDto);

        SurveyChoiceDto actualDto = dtoMapperService.mapToSurveyChoiceDto(surveyChoice);

        assertEquals(surveyChoiceDto, actualDto);
    }

    @Test
    public void testMapToSurveyChoice() {
        SurveyChoice surveyChoice = new SurveyChoice(1L, "Choice1");
        SurveyChoiceDto surveyChoiceDto = new SurveyChoiceDto(1L, "Questions");

        when(modelMapper.map(surveyChoiceDto, SurveyChoice.class)).thenReturn(surveyChoice);

        SurveyChoice actualSurvey = dtoMapperService.mapToSurveyChoice(surveyChoiceDto);

        assertEquals(surveyChoice, actualSurvey);
    }

    @Test
    public void testMapToSurveyResponseDto() {
        SurveyResponse surveyResponse = new SurveyResponse(1L);
        SurveyResponseDto surveyResponseDto = new SurveyResponseDto(1L);

        when(modelMapper.map(surveyResponse, SurveyResponseDto.class)).thenReturn(surveyResponseDto);

        SurveyResponseDto actualDto = dtoMapperService.mapToSurveyResponseDto(surveyResponse);

        assertEquals(surveyResponseDto, actualDto);
    }

    @Test
    public void testMapToSurveyResponse() {
        SurveyResponse surveyResponse = new SurveyResponse(1L);
        SurveyResponseDto surveyResponseDto = new SurveyResponseDto(1L);

        when(modelMapper.map(surveyResponseDto, SurveyResponse.class)).thenReturn(surveyResponse);

        SurveyResponse actualSurvey = dtoMapperService.mapToSurveyResponse(surveyResponseDto);

        assertEquals(surveyResponse, actualSurvey);
    }

    @Test
    public void testMapToSurveyResponseCreate() {
        SurveyResponse surveyResponse = new SurveyResponse(Set.of());
        SurveyResponseCreateDto surveyResponseCreateDto = new SurveyResponseCreateDto(Set.of());

        when(modelMapper.map(surveyResponseCreateDto, SurveyResponse.class)).thenReturn(surveyResponse);

        SurveyResponse actualSurvey = dtoMapperService.mapToSurveyResponseCreate(surveyResponseCreateDto);

        assertEquals(surveyResponse, actualSurvey);
    }

    //CategoryRequest
    @Test
    public void testMapToCategoryRequest() {
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setRequestStatus("ACCEPTED");

        RequestStatus expectedStatus = RequestStatus.ACCEPTED;

        CategoryRequest expectedRequest = new CategoryRequest();
        expectedRequest.setStatus(expectedStatus);

        when(modelMapper.map(categoryRequestDto, CategoryRequest.class)).thenReturn(expectedRequest);

        CategoryRequest result = dtoMapperService.mapToCategoryRequest(categoryRequestDto);

        assertEquals(expectedStatus, result.getStatus());
        assertEquals(expectedRequest, result);
    }

    @Test
    public void testMapToCategoryRequestCreate() {
        // arrange
        CategoryRequestCreateDto categoryRequestCreateDto = new CategoryRequestCreateDto();

        CategoryRequest expectedRequest = new CategoryRequest();

        when(modelMapper.map(categoryRequestCreateDto, CategoryRequest.class)).thenReturn(expectedRequest);

        CategoryRequest result = dtoMapperService.mapToCategoryRequestCreate(categoryRequestCreateDto);

        assertEquals(expectedRequest, result);
    }

    @Test
    public void testMapToCategoryRequestDto() {
        // arrange
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setStatus(RequestStatus.NOT_PROCESSED);

        CategoryRequestDto expectedRequestDto = new CategoryRequestDto();
        expectedRequestDto.setRequestStatus(RequestStatus.NOT_PROCESSED.toString());

        when(modelMapper.map(categoryRequest, CategoryRequestDto.class)).thenReturn(expectedRequestDto);

        CategoryRequestDto result = dtoMapperService.mapToCategoryRequestDto(categoryRequest);

        assertEquals(expectedRequestDto, result);
    }

    //Notification
    @Test
    public void testMapToNotificationType() {
        // arrange
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto();
        NotificationType expectedNotificationType = new NotificationType();

        when(modelMapper.map(notificationTypeDto, NotificationType.class)).thenReturn(expectedNotificationType);

        // act
        NotificationType result = dtoMapperService.mapToNotificationType(notificationTypeDto);

        // assert
        assertEquals(expectedNotificationType, result);
    }

    @Test
    public void testMapToNotificationTypeDto() {
        // arrange
        NotificationType notificationType = new NotificationType();
        NotificationTypeDto expectedNotificationTypeDto = new NotificationTypeDto();

        when(modelMapper.map(notificationType, NotificationTypeDto.class)).thenReturn(expectedNotificationTypeDto);

        // act
        NotificationTypeDto result = dtoMapperService.mapToNotificationTypeDto(notificationType);

        // assert
        assertEquals(expectedNotificationTypeDto, result);
    }

    @Test
    public void testMapToNotificationCategory() {
        // arrange
        NotificationCategoryDto notificationCategoryDto = new NotificationCategoryDto();
        NotificationCategory expectedNotificationCategory = new NotificationCategory();

        when(modelMapper.map(notificationCategoryDto, NotificationCategory.class)).thenReturn(expectedNotificationCategory);

        // act
        NotificationCategory result = dtoMapperService.mapToNotificationCategory(notificationCategoryDto);

        // assert
        assertEquals(expectedNotificationCategory, result);
    }

    @Test
    public void testMapToNotificationCategoryDto() {
        NotificationCategory notificationCategory = new NotificationCategory();
        NotificationCategoryDto expectedNotificationCategoryDto = new NotificationCategoryDto();

        when(modelMapper.map(notificationCategory, NotificationCategoryDto.class)).thenReturn(expectedNotificationCategoryDto);

        NotificationCategoryDto result = dtoMapperService.mapToNotificationCategoryDto(notificationCategory);

        assertEquals(expectedNotificationCategoryDto, result);
    }

    //Section

    @Test
    public void testMapToSectionFromSectionLongDto() {
        SectionLongDto sectionLongDto = new SectionLongDto();
        Section section = new Section();
        when(modelMapper.map(sectionLongDto, Section.class)).thenReturn(section);

        Section result = dtoMapperService.mapToSection(sectionLongDto);

        assertEquals(section, result);
        verify(modelMapper).map(sectionLongDto, Section.class);
    }

    @Test
    public void testMapToSectionFromSectionShortDto() {
        SectionShortDto sectionShortDto = new SectionShortDto();
        Section section = new Section();
        when(modelMapper.map(sectionShortDto, Section.class)).thenReturn(section);

        Section result = dtoMapperService.mapToSection(sectionShortDto);

        assertEquals(section, result);
        verify(modelMapper).map(sectionShortDto, Section.class);
    }

    @Test
    public void testMapToSectionLongDto() {
        Section section = new Section();
        SectionLongDto sectionLongDto = new SectionLongDto();
        when(modelMapper.map(section, SectionLongDto.class)).thenReturn(sectionLongDto);

        SectionLongDto result = dtoMapperService.mapToSectionLongDto(section);

        assertEquals(sectionLongDto, result);
        verify(modelMapper).map(section, SectionLongDto.class);
    }

    @Test
    public void testMapToSectionShortDto() {
        Section section = new Section();
        SectionShortDto sectionShortDto = new SectionShortDto();
        when(modelMapper.map(section, SectionShortDto.class)).thenReturn(sectionShortDto);

        SectionShortDto result = dtoMapperService.mapToSectionShortDto(section);

        assertEquals(sectionShortDto, result);
        verify(modelMapper).map(section, SectionShortDto.class);
    }

    @Test
    public void testMapToSection() {
        SectionCreateDto sectionCreateDto = new SectionCreateDto();
        Section section = new Section();
        when(modelMapper.map(sectionCreateDto, Section.class)).thenReturn(section);

        Section result = dtoMapperService.mapToSection(sectionCreateDto);

        assertEquals(section, result);
        verify(modelMapper).map(sectionCreateDto, Section.class);
    }

    //Event Response
    @Test
    public void testMapToEventResponse() {
        EventResponseDto eventResponseDto = new EventResponseDto();
        EventResponse eventResponse = new EventResponse();
        when(modelMapper.map(eventResponseDto, EventResponse.class)).thenReturn(eventResponse);

        EventResponse result = dtoMapperService.mapToEventResponse(eventResponseDto);


        assertEquals(eventResponse, result);
        verify(modelMapper).map(eventResponseDto, EventResponse.class);
    }

    @Test
    public void testMapToEventResponseDto() {
        EventResponse eventResponse = new EventResponse();
        EventResponseDto eventResponseDto = new EventResponseDto();
        when(modelMapper.map(eventResponse, EventResponseDto.class)).thenReturn(eventResponseDto);

        EventResponseDto result = dtoMapperService.mapToEventResponseDto(eventResponse);

        assertEquals(eventResponseDto, result);
        verify(modelMapper).map(eventResponse, EventResponseDto.class);
    }

    //Recurring Events
    @Test
    public void testMapToRecurringEventSet() {
        RecurringEventSetDto recurringEventSetDto = new RecurringEventSetDto();
        RecurringEventSet recurringEventSet = new RecurringEventSet();
        when(modelMapper.map(recurringEventSetDto, RecurringEventSet.class)).thenReturn(recurringEventSet);

        RecurringEventSet result = dtoMapperService.mapToRecurringEventSet(recurringEventSetDto);

        assertEquals(recurringEventSet, result);
        verify(modelMapper).map(recurringEventSetDto, RecurringEventSet.class);
    }

    @Test
    public void testMapToRecurringEventSetDto() {
        RecurringEventSet recurringEventSet = new RecurringEventSet();
        RecurringEventSetDto recurringEventSetDto = new RecurringEventSetDto();
        when(modelMapper.map(recurringEventSet, RecurringEventSetDto.class)).thenReturn(recurringEventSetDto);

        RecurringEventSetDto result = dtoMapperService.mapToRecurringEventSetDto(recurringEventSet);

        assertEquals(recurringEventSetDto, result);
        verify(modelMapper).map(recurringEventSet, RecurringEventSetDto.class);
    }

    @Test
    public void testMapToRecurringEventSetFromCreateDto() {
        RecurringEventSetCreateDto recurringEventSetCreateDto = new RecurringEventSetCreateDto();
        RecurringEventSet recurringEventSet = new RecurringEventSet();
        when(modelMapper.map(recurringEventSetCreateDto, RecurringEventSet.class)).thenReturn(recurringEventSet);

        RecurringEventSet result = dtoMapperService.mapToRecurringEventSet(recurringEventSetCreateDto);

        assertEquals(recurringEventSet, result);
        verify(modelMapper).map(recurringEventSetCreateDto, RecurringEventSet.class);
    }

    //Events
    @Test
    public void testMapToSingleEventFromLongDto() {
        SingleEventLongDto singleEventLongDto = new SingleEventLongDto();
        SingleEvent singleEvent = new SingleEvent();
        when(modelMapper.map(singleEventLongDto, SingleEvent.class)).thenReturn(singleEvent);

        SingleEvent result = dtoMapperService.mapToSingleEvent(singleEventLongDto);

        assertEquals(singleEvent, result);
        verify(modelMapper).map(singleEventLongDto, SingleEvent.class);
    }

    @Test
    public void testMapToSingleEventFromShortDto() {
        SingleEventShortDto singleEventShortDto = new SingleEventShortDto();
        SingleEvent singleEvent = new SingleEvent();
        when(modelMapper.map(singleEventShortDto, SingleEvent.class)).thenReturn(singleEvent);

        SingleEvent result = dtoMapperService.mapToSingleEvent(singleEventShortDto);


        assertEquals(singleEvent, result);
        verify(modelMapper).map(singleEventShortDto, SingleEvent.class);
    }

    @Test
    public void testMapToSingleEventLongDto() {
        SingleEvent singleEvent = new SingleEvent();
        SingleEventLongDto singleEventLongDto = new SingleEventLongDto();
        when(modelMapper.map(singleEvent, SingleEventLongDto.class)).thenReturn(singleEventLongDto);

        SingleEventLongDto result = dtoMapperService.mapToSingleEventLongDto(singleEvent);


        assertEquals(singleEventLongDto, result);
        verify(modelMapper).map(singleEvent, SingleEventLongDto.class);
    }

    @Test
    public void testMapToSingleEventShortDto() {
        SingleEvent singleEvent = new SingleEvent();
        SingleEventShortDto singleEventShortDto = new SingleEventShortDto();
        when(modelMapper.map(singleEvent, SingleEventShortDto.class)).thenReturn(singleEventShortDto);

        SingleEventShortDto result = dtoMapperService.mapToSingleEventShortDto(singleEvent);

        assertEquals(singleEventShortDto, result);
        verify(modelMapper).map(singleEvent, SingleEventShortDto.class);
    }


    @Test
    public void testMapToSingleEventGuestDto() {
        SingleEvent singleEvent = new SingleEvent();
        SingleEventGuestDto expectedDto = new SingleEventGuestDto();
        when(modelMapper.map(singleEvent, SingleEventGuestDto.class)).thenReturn(expectedDto);

        SingleEventGuestDto actualDto = dtoMapperService.mapToSingleEventGuestDto(singleEvent);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    public void testMapToSingleEvent() {
        SingleEventCreateDto createDto = new SingleEventCreateDto();
        SingleEvent expectedEvent = new SingleEvent();
        when(modelMapper.map(createDto, SingleEvent.class)).thenReturn(expectedEvent);

        SingleEvent actualEvent = dtoMapperService.mapToSingleEvent(createDto);

        assertEquals(expectedEvent, actualEvent);
    }

    //Event Comment
    @Test
    void mapToEventCommentDto() {
    EventComment eventComment = new EventComment();
        eventComment.setCommentId(1L);
        eventComment.setComment("Test comment");

    EventCommentDto expectedDto = new EventCommentDto();
        expectedDto.setCommentId(1L);
        expectedDto.setComment("Test comment");

    when(modelMapper.map(eventComment, EventCommentDto.class)).thenReturn(expectedDto);

    EventCommentDto result = dtoMapperService.mapToEventCommentDto(eventComment);

    assertEquals(expectedDto.getCommentId(), result.getCommentId());
    assertEquals(expectedDto.getComment(), result.getComment());
}

    @Test
    public void testMapToEventComment1() {
        EventCommentDto eventCommentDto = new EventCommentDto();
        eventCommentDto.setComment("CommentToEvent");

        EventComment eventComment = new EventComment();
        eventComment.setComment("CommentToEvent");

        when(modelMapper.map(eventCommentDto, EventComment.class)).thenReturn(eventComment);

        EventComment actualEventComment = dtoMapperService.mapToEventComment(eventCommentDto);

        assertEquals(eventComment, actualEventComment);
    }


    @Test
    public void testMapToEventComment() {
        EventCommentCreateDto eventCommentCreateDto = new EventCommentCreateDto();
        EventComment eventComment = new EventComment();

        when(modelMapper.map(eventCommentCreateDto, EventComment.class)).thenReturn(eventComment);

        EventComment result = dtoMapperService.mapToEventComment(eventCommentCreateDto);

        assertEquals(eventComment, result);
    }

    //Request Comments
    @Test
    public void testMapToRequestComment() {
        RequestCommentDto requestCommentDto = new RequestCommentDto();
        RequestComment requestComment = new RequestComment();

        when(modelMapper.map(requestCommentDto, RequestComment.class)).thenReturn(requestComment);

        RequestComment result = dtoMapperService.mapToRequestComment(requestCommentDto);

        assertEquals(requestComment, result);
    }

    @Test
    public void testMapToRequestCommentDto() {
        RequestComment requestComment = new RequestComment();
        RequestCommentDto requestCommentDto = new RequestCommentDto();

        when(modelMapper.map(requestComment, RequestCommentDto.class)).thenReturn(requestCommentDto);

        RequestCommentDto result = dtoMapperService.mapToRequestCommentDto(requestComment);

        assertEquals(requestCommentDto, result);
    }

    @Test
    public void testMapToRequestCommentCreateDto() {
        RequestCommentCreateDto requestCommentCreateDto = new RequestCommentCreateDto();
        RequestComment requestComment = new RequestComment();

        when(modelMapper.map(requestCommentCreateDto, RequestComment.class)).thenReturn(requestComment);

        RequestComment result = dtoMapperService.mapToRequestComment(requestCommentCreateDto);

        assertEquals(requestComment, result);
    }
}