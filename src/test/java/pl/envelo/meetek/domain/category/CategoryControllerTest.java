package pl.envelo.meetek.domain.category;

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
import pl.envelo.meetek.domain.category.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private CategoryValidator categoryValidator;

    @InjectMocks
    private CategoryController categoryController;

    //GetCategory
    // get Category 200 :: OK
    @Test
    public void testGetCategory_ReturnSuccess() {
        long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(categoryId);
        when(categoryService.getCategoryById(categoryId)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.getCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }

    @Test
    public void getCategory_Successful() {
        // Arrange
        long categoryId = 1L;
        CategoryDto expectedCategory = new CategoryDto();
        expectedCategory.setCategoryId(categoryId);
        expectedCategory.setName("Books");

        when(categoryService.getCategoryById(categoryId)).thenReturn(expectedCategory);

        // Act
        ResponseEntity<CategoryDto> result = categoryController.getCategory(categoryId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedCategory, result.getBody());
    }


    //create
    @Test
    public void testCreateCategorySuccess() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Test Category");

        CategoryDto createdCategoryDto = new CategoryDto();
        createdCategoryDto.setCategoryId(1L);
        createdCategoryDto.setName("Test Category");

        when(categoryService.createCategory(categoryDto)).thenReturn(createdCategoryDto);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        ResponseEntity<Void> response = categoryController.createCategory(categoryDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/1", response.getHeaders().getLocation().getPath());
    }

    //test edit Categories
    @Test
    public void testEditCategory_ReturnSuccess() {
        long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto();

        ResponseEntity<Void> response = categoryController.editCategory(categoryId, categoryDto);

        verify(categoryService).editCategory(categoryId, categoryDto);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    //test Get all active Categories
    @Test
    public void testGetAllActiveCategories_ReturnSuccess() {
        List<CategoryDto> categories = new ArrayList<>();
        categories.add(new CategoryDto(1L, "Category 1", true));
        categories.add(new CategoryDto(2L, "Category 2", true));
        when(categoryService.getAllActiveCategories()).thenReturn(categories);

        ResponseEntity<List<CategoryDto>> response = categoryController.getAllActiveCategories();

        HttpStatus expectedStatus = categories.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        assertEquals(response.getStatusCode(), expectedStatus);
        assertEquals(response.getBody(), categories);
    }
}

