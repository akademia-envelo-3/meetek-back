package pl.envelo.meetek.domain.hashtag;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.envelo.meetek.domain.category.CategoryDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HashtagControllerTest {

    @Mock
    private HashtagService hashtagService;

    @Mock
    private HashtagRepo hashtagRepo;

    @Mock
    private HashtagValidator hashtagValidator;

    @InjectMocks
    private HashtagController hashtagController;

    //Gethashtag
    @Test
    public void testGetHashtag_ReturnSuccess() {
        long hashtagId = 1L;
        HashtagDto hashtagDto = new HashtagDto();
        hashtagDto.setHashtagId(hashtagId);

        when(hashtagService.getHashtagById(hashtagId)).thenReturn(hashtagDto);

        ResponseEntity<HashtagDto> response = hashtagController.getHashtag(hashtagId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hashtagDto, response.getBody());
    }

/*    @Test
    public void testCreateHashtagSuccess() {
        HashtagCreateDto hashtagDto = new HashtagCreateDto();
        hashtagDto.setName("#HASHTAG");

        HashtagCreateDto createdDto = new HashtagCreateDto();
        createdDto.setHashtagId(1L);
        createdDto.setName("Test #HASHTAG");

        Hashtag hashtag = new Hashtag();
        hashtag.setHashtagId(createdDto.getHashtagId());
        hashtag.setName(createdDto.getName());

       when(hashtagService.createHashtag(createdDto)).thenReturn(createdDto);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        ResponseEntity<Void> response = hashtagController.saveNewHashtag(createdDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/1", response.getHeaders().getLocation().getPath());
    }*/

    @Test
    public void testEditHashtag_ReturnSuccess() {
        long hashtagId = 1L;
        HashtagCreateDto createDto = new HashtagCreateDto();

        ResponseEntity<Void> response = hashtagController.editHashtag(hashtagId, createDto);

        verify(hashtagService).editHashtag(hashtagId, createDto);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


    @Test
    void testGetAllActiveHashtags_ReturnSuccess1() {
        List<HashtagDto> hashtagDto = new ArrayList<>();
        hashtagDto.add(new HashtagDto(1L, "Category 1", true,2));
        hashtagDto.add(new HashtagDto(2L, "Category 2", true,7));

        when(hashtagService.getAllActiveHashtags()).thenReturn(hashtagDto);

        HashtagController hashtagController = new HashtagController(hashtagService);
        ResponseEntity<List<HashtagDto>> response = hashtagController.getActiveHashtags();

        HttpStatus expectedStatus = hashtagDto.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        assertEquals(response.getStatusCode(), expectedStatus);
        assertEquals(response.getBody(), hashtagDto);
    }

}