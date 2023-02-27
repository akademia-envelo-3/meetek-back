package pl.envelo.meetek.domain.group;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.envelo.meetek.domain.group.model.SectionCreateDto;
import pl.envelo.meetek.domain.group.model.SectionShortDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SectionControllerTest {

    @Mock
    private SectionService sectionService;

    @Mock
    private SectionRepo sectionRepo;

    @Mock
    private SectionValidator sectionValidator;

    @InjectMocks
    private SectionController sectionController;
}

    //GetSection
 /*   @Test
    public void testGetSection_ReturnSuccess() {
        long sectionId = 1L;
        SectionLongtDto sectionShortDto = new SectionLongtDto();
        SectionLongtDto.(categoryId);
        when(categoryService.getCategoryById(categoryId)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.getCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }
}
*/