package pl.envelo.meetek.utils;

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
import pl.envelo.meetek.domain.coordinates.Coordinates;
import pl.envelo.meetek.domain.coordinates.CoordinatesDto;
import pl.envelo.meetek.domain.hashtag.Hashtag;
import pl.envelo.meetek.domain.hashtag.HashtagCreateDto;
import pl.envelo.meetek.domain.hashtag.HashtagDto;
import pl.envelo.meetek.domain.survey.model.Survey;
import pl.envelo.meetek.domain.survey.model.SurveyCreateDto;
import pl.envelo.meetek.domain.survey.model.SurveyDto;
import pl.envelo.meetek.domain.user.model.*;

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
        HashtagDto hashtagDto = new HashtagDto("example");
        HashtagCreateDto expectedCreateDto = new HashtagCreateDto("example");
        when(modelMapper.map(hashtagDto, HashtagCreateDto.class)).thenReturn(expectedCreateDto);

        HashtagCreateDto actualCreateDto = dtoMapperService.mapToHashtagCreateDto(hashtagDto);

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
        Guest guest = new Guest(1L,"Arek", "Morlinek","ddd@ddd.dd");
        GuestDto guestDto = new GuestDto(1L,"Arek", "Morlinek","ddd@ddd.dd");

        when(modelMapper.map(guest, GuestDto.class)).thenReturn(guestDto);

        GuestDto actualDto = dtoMapperService.mapToGuestDto(guest);

        assertEquals(guestDto, actualDto);
    }

    @Test
    public void testMapToGuest() {
        GuestDto guestDto = new GuestDto(1L,"Arek", "Morlinek","ddd@ddd.dd");
        Guest guest = new Guest(1L,"Arek", "Morlinek","ddd@ddd.dd");

        when(modelMapper.map(guestDto, Guest.class)).thenReturn(guest);

        Guest actualGuest = dtoMapperService.mapToGuest(guestDto);

        assertEquals(guest, actualGuest);
    }

    //Surveys
    @Test
    public void testMapToSurveyDto() {
        Survey survey = new Survey(1L,"Questions");
        SurveyDto surveyDto = new SurveyDto(1L, "Questions");

        when(modelMapper.map(survey, SurveyDto.class)).thenReturn(surveyDto);

        SurveyDto actualDto = dtoMapperService.mapToSurveyDto(survey);

        assertEquals(surveyDto, actualDto);
    }

    @Test
    public void testMapToSurvey() {
        Survey survey = new Survey(1L,"Questions");
        SurveyDto surveyDto = new SurveyDto(1L, "Questions");

        when(modelMapper.map(surveyDto, Survey.class)).thenReturn(survey);

        Survey actualSurvey = dtoMapperService.mapToSurvey(surveyDto);

        assertEquals(survey, actualSurvey);
    }

    @Test
    public void testMapToCreateSurveyDto() {
        SurveyDto surveyDto = new SurveyDto("Questions");
        SurveyCreateDto surveyCreateDto = new SurveyCreateDto("Questions");

        when(modelMapper.map(surveyDto, SurveyCreateDto.class)).thenReturn(surveyCreateDto);

        SurveyCreateDto actualCreateDto = dtoMapperService.mapToSurveyCreateDto(surveyDto);


        assertEquals(surveyCreateDto, actualCreateDto);
    }
}