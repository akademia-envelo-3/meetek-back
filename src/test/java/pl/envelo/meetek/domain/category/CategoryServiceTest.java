package pl.envelo.meetek.domain.category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import pl.envelo.meetek.domain.category.*;
import pl.envelo.meetek.domain.request.model.CategoryRequest;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private CategoryValidator categoryValidator;

    @Mock
    private DtoMapperService mapperService;

    @InjectMocks
    private CategoryService categoryService;

    //Tests to get Category by id: Success and Failure
    @Test
    void testGetCategoryById_ResultSuccess() {
        long categoryId = 1L;
        Category category = new Category();
        CategoryDto expected = new CategoryDto();

        when(categoryValidator.validateExists(categoryId)).thenReturn(category);
        when(mapperService.mapToCategoryDto(category)).thenReturn(expected);

        assertNotNull(category);
        CategoryDto result = categoryService.getCategoryById(categoryId);

        assertEquals(expected, result);
        verify(categoryValidator).validateExists(categoryId);
        verify(mapperService).mapToCategoryDto(category);
    }

    @Test
    void testGetCategoryById_ResultNotFoundException() {
        long categoryId = 1L;
        String message = "Category not found";

        when(categoryValidator.validateExists(categoryId)).thenThrow(new NotFoundException(message));

        assertThrows(NotFoundException.class, () -> categoryService.getCategoryById(categoryId));

        verify(categoryValidator).validateExists(categoryId);
        verify(mapperService, never()).mapToCategoryDto(any());
    }

    //Tests to get Category by Name: Success and Failure

    @Test
    void testGetCategoryByName_ResultSuccess() {
        Category expectedCategory = new Category();
        expectedCategory.setName("Test Category");
        when(categoryValidator.validateNotActiveDuplicate("Test Category")).thenReturn(expectedCategory);

        Category actualCategory = categoryService.getCategoryByName("Test Category");

        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    void testGetCategoryByName_ResultFailure() {
        when(categoryValidator.validateNotActiveDuplicate("Test Category")).thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class, () -> categoryService.getCategoryByName("Test Category"));
    }

    //Tests to create Category: Success and Failure
    @Test
    void testCreateCategory_ResultSuccess() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Category 1");

        Category category = new Category();
        category.setName("Category 1");

        when(mapperService.mapToCategory(categoryDto)).thenReturn(category);
        doNothing().when(categoryValidator).validateInput(category);
        doNothing().when(categoryValidator).validateNotDuplicate(category.getName());

        when(categoryRepo.save(category)).thenReturn(category);
        when(mapperService.mapToCategoryDto(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.createCategory(categoryDto);

        assertNotNull(result);
        assertEquals("Category 1", result.getName());
    }


    //test create or activate cat
    @Test
    void testCreateCategory_ResultSuccess1() {
        CategoryRequest request = new CategoryRequest("Test Category");
        Category category = new Category("Test Category");

        when(categoryRepo.save(any(Category.class))).thenReturn(category);

        categoryService.createOrActivateCategory(request);

        verify(categoryRepo, times(1)).save(any(Category.class));
        verify(categoryValidator, never()).validateNotDuplicate(anyString());
    }


    //edit
    @Test
    public void testEditCategory_ResultFailure() {
        long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        Category category = new Category();
        Category categoryFromDto = new Category();

        when(categoryValidator.validateExists(categoryId)).thenReturn(category);
        when(mapperService.mapToCategory(categoryDto)).thenReturn(categoryFromDto);
        doThrow(IllegalArgumentException.class).when(categoryValidator).validateInput(categoryFromDto);

        assertThrows(IllegalArgumentException.class, () -> categoryService.editCategory(categoryId, categoryDto));
    }

    //Get all categories
    @Test
    public void testGetAllCategories_ReturnSuccess() {
        Category category1 = new Category("Category 1", true);
        Category category2 = new Category("Category 2", true);

        when(categoryRepo.findAll(any(Sort.class))).thenReturn(Arrays.asList(category1, category2));
        when(mapperService.mapToCategoryDto(category1)).thenReturn(new CategoryDto(1, "Category 1", true));
        when(mapperService.mapToCategoryDto(category2)).thenReturn(new CategoryDto(2, "Category 2", true));

        List<CategoryDto> result = categoryService.getAllCategories();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Category 1");
        assertThat(result.get(0).isActive()).isTrue();
        assertThat(result.get(1).getName()).isEqualTo("Category 2");
        assertThat(result.get(1).isActive()).isTrue();
        verify(categoryRepo).findAll(any(Sort.class));
        verify(mapperService).mapToCategoryDto(category1);
        verify(mapperService).mapToCategoryDto(category2);
    }

    @Test
    public void testGetAllCategories_ReturnCategoriesSuccess() {
        List<Category> expectedCategories = Collections.singletonList(new Category());
        when(categoryRepo.findAll(Sort.by("name"))).thenReturn(expectedCategories);
        when(mapperService.mapToCategoryDto(expectedCategories.get(0))).thenReturn(new CategoryDto());

        List<CategoryDto> actualCategories = categoryService.getAllCategories();

        assertEquals(expectedCategories.size(), actualCategories.size());
    }

    @Test
    public void testGetAllCategoriesAndReturnEmptyList_ResultSuccess() {
        when(categoryRepo.findAll(Sort.by("name"))).thenReturn(Collections.emptyList());

        List<CategoryDto> result = categoryService.getAllCategories();

        assertThat(result).isEmpty();
    }

    //active
    @Test
    public void testGetAllActiveCategoriesAndActiveCategories_WhenFound() {
        Category category1 = new Category("Category 1", true);
        Category category2 = new Category("Category 2", true);
        List<Category> categories = Arrays.asList(category1, category2);

        CategoryDto categoryDto1 = new CategoryDto(1, "Category 1", true);
        CategoryDto categoryDto2 = new CategoryDto(2, "Category 2", true);
        List<CategoryDto> categoryDtos = Arrays.asList(categoryDto1, categoryDto2);

        when(categoryRepo.findAllByIsActiveTrueOrderByName()).thenReturn(categories);
        when(mapperService.mapToCategoryDto(any(Category.class))).thenReturn(categoryDto1, categoryDto2);

        List<CategoryDto> result = categoryService.getAllActiveCategories();

        assertEquals(categoryDtos, result);
        verify(categoryRepo).findAllByIsActiveTrueOrderByName();
        verify(mapperService).mapToCategoryDto(category1);
        verify(mapperService).mapToCategoryDto(category2);
    }

    @Test
    public void testGetAllActiveCategories_ReturnFailure() {
        CategoryService categoryService = new CategoryService(categoryRepo, categoryValidator, mapperService);

        when(categoryRepo.findAllByIsActiveTrueOrderByName()).thenThrow(new RuntimeException("Error in repository"));

        Exception exception = assertThrows(RuntimeException.class,
                () -> categoryService.getAllActiveCategories());

        assertEquals("Error in repository", exception.getMessage());
    }

    //Active category
    @Test
    public void testGetActivateCategory_ReturnSuccess() {
        CategoryService categoryService = new CategoryService(categoryRepo, categoryValidator, mapperService);

        Category category = new Category("Category 1", false);
        categoryService.activateCategory(category);

        verify(categoryRepo, times(1)).save(category);
        assertTrue(category.isActive());
    }

    @Test
    public void testGetActivateCategory_RetrunFailure() {
        CategoryService categoryService = new CategoryService(categoryRepo, categoryValidator, mapperService);

        Category category = new Category("Category 1", false);
        doThrow(new RuntimeException("Error in repository")).when(categoryRepo).save(category);

        Exception exception = assertThrows(RuntimeException.class,
                () -> categoryService.activateCategory(category));

        assertEquals("Error in repository", exception.getMessage());
    }

    @Test
    public void testUpdateFields_ReturnSuccess() {
        Category category = new Category();
        category.setName("Old name");
        category.setActive(false);

        Category categoryFromDto = new Category();
        categoryFromDto.setName("New name");
        categoryFromDto.setActive(true);

        categoryService.updateFields(category, categoryFromDto);

        assertEquals("New name", category.getName());
        assertEquals(true, category.isActive());
    }

    @Test
    public void testUpdateFields_ReturnFailure() {
        Category category = new Category();
        category.setName("Old name");
        category.setActive(false);

        Category categoryFromDto = new Category();
        categoryFromDto.setName("Wrong name");
        categoryFromDto.setActive(false);

        categoryService.updateFields(categoryFromDto, category);

        assertEquals("Old name", category.getName());
        assertEquals(false, category.isActive());
    }

}