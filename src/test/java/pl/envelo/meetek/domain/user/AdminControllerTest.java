package pl.envelo.meetek.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.envelo.meetek.domain.category.CategoryDto;
import pl.envelo.meetek.domain.category.CategoryService;
import pl.envelo.meetek.domain.event.SingleEventService;
import pl.envelo.meetek.domain.event.model.SingleEventShortDto;
import pl.envelo.meetek.domain.group.SectionService;
import pl.envelo.meetek.domain.hashtag.HashtagDto;
import pl.envelo.meetek.domain.hashtag.HashtagService;
import pl.envelo.meetek.domain.request.CategoryRequestService;
import pl.envelo.meetek.domain.request.model.CategoryRequestDto;
import pl.envelo.meetek.domain.user.AdminController;
import pl.envelo.meetek.domain.user.AdminService;
import pl.envelo.meetek.domain.user.model.Admin;
import pl.envelo.meetek.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    private SingleEventService singleEventService;

    @Mock
    private SectionService sectionService;
    @Mock
    private AdminService adminService;

    @Mock
    private CategoryRequestService categoryRequestService;
    @Mock
    private HashtagService hashtagService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private AdminController adminController;

/*    @Test
    public void testGetAllEventsBeforeToday_ReturnEmptyListAndStatusNoContent() {
        AdminController adminController = new AdminController(singleEventService, null, null, null, null, null);
        when(singleEventService.getAllEventsBeforeToday()).thenReturn(new ArrayList<>());

        ResponseEntity<List<SingleEventShortDto>> response = adminController.getAllEventsBeforeToday();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    public void testGetAllEventsBeforeToday_ReturnSuccessfulAndStatusOk() {
        List<SingleEventShortDto> events = new ArrayList<>();
        events.add(new SingleEventShortDto());
        when(singleEventService.getAllEventsBeforeToday()).thenReturn(events);

        AdminController adminController = new AdminController(singleEventService, sectionService, null, null, null, null);
        ResponseEntity<List<SingleEventShortDto>> responseEntity = adminController.getAllEventsBeforeToday();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
    }*/

/*    @Test
    public void testGetAllEventsAfterToday_ReturnsNoContentAndEmptyList() {
        when(singleEventService.getAllEventsAfterToday()).thenReturn(new ArrayList<>());

        AdminController adminController = new AdminController(singleEventService, sectionService, null, null, null, null);
        ResponseEntity<List<SingleEventShortDto>> responseEntity = adminController.getAllEventsAfterToday();

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }*/

/*    @Test
    public void testGetAllEventsAfterToday_ReturnsSuccessfulAndOkStatus() {
        List<SingleEventShortDto> events = new ArrayList<>();
        events.add(new SingleEventShortDto());
        when(singleEventService.getAllEventsAfterToday()).thenReturn(events);

        AdminController adminController = new AdminController(singleEventService, sectionService, null, null, null, null);
        ResponseEntity<List<SingleEventShortDto>> responseEntity = adminController.getAllEventsAfterToday();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
    }*/

    @Test
    void testGetAllHashtags_ReturnsEmptyListAndStatusNoContent() {
        List<HashtagDto> emptyList = new ArrayList<>();
        when(hashtagService.getAllHashtags()).thenReturn(emptyList);

        ResponseEntity<List<HashtagDto>> response = adminController.getAllHashtags();
        HttpStatus expectedStatus = HttpStatus.NO_CONTENT;

        assert (response.getStatusCode() == expectedStatus);
        assert (response.getBody().isEmpty());
    }

    @Test
    void testGetAllHashtags_ReturnsSuccessfulAndOkStatus() {
        List<HashtagDto> hashtagList = new ArrayList<>();
        hashtagList.add(new HashtagDto("tag1"));
        hashtagList.add(new HashtagDto("tag2"));
        when(hashtagService.getAllHashtags()).thenReturn(hashtagList);

        ResponseEntity<List<HashtagDto>> response = adminController.getAllHashtags();
        HttpStatus expectedStatus = HttpStatus.OK;

        assert (response.getStatusCode() == expectedStatus);
        assert (response.getBody().size() == 2);
        assert (response.getBody().get(0).getName().equals("tag1"));
        assert (response.getBody().get(1).getName().equals("tag2"));
    }

    @Test
    public void testGetAllCategories_ResultsSuccessful() {
        CategoryDto category1 = new CategoryDto(1L, "Category 1");
        CategoryDto category2 = new CategoryDto(2L, "Category 2");
        List<CategoryDto> categories = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<CategoryDto>> response = adminController.getAllCategories();

        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals(categories));
    }

    @Test
    public void testGetAllCategories_ReturnsEmptyListAndNoContentStatus() {
        List<CategoryDto> categories = Arrays.asList();

        when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<CategoryDto>> response = adminController.getAllCategories();

        assert (response.getStatusCode() == HttpStatus.NO_CONTENT);
        assert (response.getBody().equals(categories));
    }

    @Test
    public void testGetAllNotProcessedCategoryRequests_ReturnsSuccessfulAndStatusOk() {
        List<CategoryRequestDto> categoryRequestDtos = new ArrayList<>();
        categoryRequestDtos.add(new CategoryRequestDto("Category 1"));
        when(categoryRequestService.getAllNotProcessedRequests()).thenReturn(categoryRequestDtos);

        ResponseEntity<List<CategoryRequestDto>> response = adminController.getAllNotProcessedCategoryRequests();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryRequestDtos, response.getBody());
    }

    @Test
    public void testGetAllNotProcessedCategoryRequests_ReturnsEmptyListAndStatusNoContent() {
        List<CategoryRequestDto> categoryRequestDtos = new ArrayList<>();
        when(categoryRequestService.getAllNotProcessedRequests()).thenReturn(categoryRequestDtos);

        ResponseEntity<List<CategoryRequestDto>> response = adminController.getAllNotProcessedCategoryRequests();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(categoryRequestDtos, response.getBody());
    }

    @Test
    public void testReplyToCategoryRequest_ReturnValidRequest() {
        long categoryRequestId = 1L;
        long userId = 2L;
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        Admin admin = new Admin();
        when(adminService.getById(userId)).thenReturn(admin);
        doNothing().when(categoryRequestService).replyToRequest(categoryRequestId, admin, categoryRequestDto);

        ResponseEntity<Void> responseEntity = adminController.replyToCategoryRequest(categoryRequestId, userId, categoryRequestDto);

        verify(adminService).getById(userId);
        verify(categoryRequestService).replyToRequest(categoryRequestId, admin, categoryRequestDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testEditEventOwner() {
        long eventId = 1L;
        long adminId = 2L;
        long newOwnerId = 3L;
        Admin admin = new Admin();
        when(adminService.getById(adminId)).thenReturn(admin);

        ResponseEntity<Void> response = adminController.editEventOwner(eventId, adminId, newOwnerId);

        verify(singleEventService, times(1)).setEventOwnerByAdmin(newOwnerId, eventId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testEditSectionOwner() {
        long sectionId = 1L;
        long adminId = 2L;
        long newOwnerId = 3L;
        Admin admin = new Admin();
        when(adminService.getById(adminId)).thenReturn(admin);

        ResponseEntity<Void> response = adminController.editSectionOwner(sectionId, adminId, newOwnerId);

        verify(sectionService, times(1)).setSectionOwnerByAdmin(newOwnerId, sectionId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}