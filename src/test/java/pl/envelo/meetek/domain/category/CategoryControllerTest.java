package pl.envelo.meetek.domain.category;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

 /*   @Test
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

    @Test
    void testCreateCategoryReturnsCreatedStatus() {
        CategoryDto categoryDto = new CategoryDto();
        CategoryDto createdCategory = new CategoryDto();
        createdCategory.setCategoryId(1L);

        when(categoryService.createCategory(categoryDto)).thenReturn(createdCategory);

        ResponseEntity<Void> response = categoryController.createCategory(categoryDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetCategoryReturnsCategory() {
        long categoryId = 1L;
        CategoryDto expectedCategory = new CategoryDto();
        expectedCategory.setCategoryId(categoryId);

        when(categoryService.getCategoryById(categoryId)).thenReturn(expectedCategory);

        ResponseEntity<CategoryDto> response = categoryController.getCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCategory, response.getBody());
    }

    @Test
    public void testGetCategoryReturnsInvalidCategoryIdError() {
        long invalidCategoryId = 0L;

        doThrow(new InvalidCategoryIdException()).when(categoryService).getCategoryById(invalidCategoryId);

        ResponseEntity<CategoryDto> response = categoryController.getCategory(invalidCategoryId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetCategoryReturnsNotFoundError() {
        long nonExistentCategoryId = 999L;

        doThrow(new ()).when(categoryService).getCategoryById(nonExistentCategoryId);

        ResponseEntity<CategoryDto> response = categoryController.getCategory(nonExistentCategoryId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }*/
    @Test
    public void testGetCategoryReturnsInternalServerError() {
        long categoryId = 1L;

        doThrow(new RuntimeException()).when(categoryService).getCategoryById(categoryId);

        ResponseEntity<CategoryDto> response = categoryController.getCategory(categoryId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCreateCategoryReturnsCreated() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Test category");

        CategoryDto createdCategory = new CategoryDto();
        createdCategory.setCategoryId(1L);
        createdCategory.setName("Test category");

        when(categoryService.createCategory(categoryDto)).thenReturn(createdCategory);

        ResponseEntity<Void> response = categoryController.createCategory(categoryDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().getLocation());
        assertEquals("/1", response.getHeaders().getLocation().getPath());
    }

    @Test
    public void testCreateCategoryReturnsBadRequest() {
        CategoryDto invalidCategoryDto = new CategoryDto();

        ResponseEntity<Void> response = categoryController.createCategory(invalidCategoryDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    public void testGetCategory() {
        long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto(categoryId, "Category", true);
        when(categoryService.getCategoryById(categoryId)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> responseEntity = categoryController.getCategory(categoryId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(categoryDto, responseEntity.getBody());
    }
}

