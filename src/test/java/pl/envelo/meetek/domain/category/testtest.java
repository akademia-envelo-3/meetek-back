package pl.envelo.meetek.domain.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class testtest {
    @ExtendWith(MockitoExtension.class)
    public class CategoryServiceTest {

        @Mock
        private CategoryRepo categoryRepo;

        @Mock
        private CategoryValidator categoryValidator;

        @Mock
        private DtoMapperService mapperService;

        @InjectMocks
        private CategoryService categoryService;

        @Test
        public void getCategoryById_Success() {
            // arrange
            long categoryId = 1L;
            Category category = new Category();
            CategoryDto expected = new CategoryDto();

            when(categoryValidator.validateExists(categoryId)).thenReturn(category);
            when(mapperService.mapToCategoryDto(category)).thenReturn(expected);

            // act
            CategoryDto result = categoryService.getCategoryById(categoryId);

            // assert
            assertEquals(expected, result);
            verify(categoryValidator).validateExists(categoryId);
            verify(mapperService).mapToCategoryDto(category);
        }
    }
    package test.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    public class AdminTest package pl.envelo.meetek.controller.user;

        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import pl.envelo.meetek.dto.category.CategoryDto;
        import pl.envelo.meetek.dto.event.SingleEventShortDto;
        import pl.envelo.meetek.dto.hashtag.HashtagDto;
        import pl.envelo.meetek.model.category.Category;
        import pl.envelo.meetek.model.event.SingleEvent;
        import pl.envelo.meetek.model.notification.hashtag.Hashtag;
        import pl.envelo.meetek.service.DtoMapperService;
        import pl.envelo.meetek.service.category.CategoryService;
        import pl.envelo.meetek.service.event.SingleEventService;
        import pl.envelo.meetek.service.hashtag.HashtagService;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.assertNull;
        import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    class AdminControllerTest {

        @Mock
        private SingleEventService singleEventService;

        @Mock
        private CategoryService categoryService;

        @Mock
        private HashtagService hashtagService;

        @Mock
        private DtoMapperService dtoMapperService;

        @InjectMocks
        private AdminController adminController;

        @Test
        void getAllEventsBeforeTodayShouldReturnOkWithEvents() {
            SingleEvent singleEvent = new SingleEvent();
            SingleEventShortDto singleEventShortDto = new SingleEventShortDto();
            List<SingleEvent> pastEvents = Collections.singletonList(singleEvent);
            List<SingleEventShortDto> pastEventsShortDto = Collections.singletonList(singleEventShortDto);

            when(singleEventService.getAllEventsBeforeToday()).thenReturn(pastEvents);
            when(dtoMapperService.mapToSingleEventShortDto(singleEvent)).thenReturn(singleEventShortDto);

            ResponseEntity<List<SingleEventShortDto>> result = adminController.getAllEventsBeforeToday();

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(pastEventsShortDto, result.getBody());
        }

        @Test
        void getAllEventsBeforeTodayReturnNoContentStatus() {
            when(singleEventService.getAllEventsBeforeToday()).thenReturn(Collections.emptyList());

            ResponseEntity<List<SingleEventShortDto>> result = adminController.getAllEventsBeforeToday();

            assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        }

        @Test
        void getAllEventsAfterTodayEventsFoundReturnOkStatus() {
            List<SingleEvent> futureEvents = new ArrayList<>();
            List<SingleEventShortDto> futureEventsShortDto = new ArrayList<>();
            futureEvents.add(new SingleEvent());
            futureEventsShortDto.add(new SingleEventShortDto());

            when(singleEventService.getAllEventsAfterToday()).thenReturn(futureEvents);
            when(dtoMapperService.mapToSingleEventShortDto(futureEvents.get(0)))
                    .thenReturn(futureEventsShortDto.get(0));

            ResponseEntity<List<SingleEventShortDto>> result = adminController.getAllEventsAfterToday();

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(futureEventsShortDto, result.getBody());
        }

        @Test
        void getAllEventsAfterTodayNoEventFoundReturnNotFound() {
            when(singleEventService.getAllEventsAfterToday()).thenReturn(new ArrayList<>());

            ResponseEntity<List<SingleEventShortDto>> result = adminController.getAllEventsAfterToday();

            assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        }

        @Test
        void testGetAllCategoriesAndReturnOkStatus() {
            List<Category> categories = new ArrayList<>();
            categories.add(new Category("Category1"));
            categories.add(new Category());


            List<CategoryDto> categoryDto = new ArrayList<>();
            categoryDto.add(new CategoryDto());
            categoryDto.add(new CategoryDto());

            when(categoryService.getAllCategories()).thenReturn(categories);
            when(dtoMapperService.mapToCategoryDto(categories.get(0))).thenReturn(categoryDto.get(0));
            when(dtoMapperService.mapToCategoryDto(categories.get(1))).thenReturn(categoryDto.get(1));

            ResponseEntity<List<CategoryDto>> response = adminController.getAllCategories();
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(categoryDto, response.getBody());
        }

        @Test
        void testGetAllCategoriesReturnNotFoundStatus() {
            List<Category> categories = new ArrayList<>();

            when(categoryService.getAllCategories()).thenReturn(categories);
            ResponseEntity<List<CategoryDto>> response = adminController.getAllCategories();

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            assertEquals(categories,response.getBody());
            verify(categoryService).getAllCategories();
            verifyNoMoreInteractions(dtoMapperService);

        }

        @Test
        void testGetAllHashtagsandReturnFoundStatus() {
            List<Hashtag> hashtags = new ArrayList<>();
            hashtags.add(new Hashtag(1L, "#DSDS", true, 1));
            hashtags.add(new Hashtag(2L, "#DSDS2", true, 4));

            List<HashtagDto> hashtagDto = new ArrayList<>();
            hashtagDto.add(new HashtagDto());
            hashtagDto.add(new HashtagDto());

            when(hashtagService.getAllHashtags()).thenReturn(hashtags);
            when(dtoMapperService.mapToHashtagDto(hashtags.get(0))).thenReturn(hashtagDto.get(0));
            when(dtoMapperService.mapToHashtagDto(hashtags.get(1))).thenReturn(hashtagDto.get(1));

            ResponseEntity<List<HashtagDto>> response = adminController.getAllHashtags();
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(hashtagDto, response.getBody());
        }

        @Test
        void testGetAllHashtagsAndReturnNotFoundStatus() {
            List<Hashtag> hashtags = new ArrayList<>();

            when(hashtagService.getAllHashtags()).thenReturn(hashtags);

            ResponseEntity<List<HashtagDto>> response = adminController.getAllHashtags();

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            assertEquals(hashtags,response.getBody());
            verify(hashtagService).getAllHashtags();
            verifyNoMoreInteractions(dtoMapperService);

        }

    }
    {
    }

}
