package pl.envelo.meetek.domain.category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.category.Category;
import pl.envelo.meetek.domain.category.CategoryRepo;
import pl.envelo.meetek.domain.category.CategoryValidator;
import pl.envelo.meetek.exceptions.DuplicateException;
import pl.envelo.meetek.exceptions.NotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryValidatorTest {

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private CategoryValidator categoryValidator;

    //Exists
    @Test
    void testValidateCategory_whenExists() {
        Category category = new Category();
        category.setCategoryId(1L);
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
        CategoryValidator validator = new CategoryValidator(null, categoryRepo);

        Category validatedCategory = validator.validateExists(1L);

        assertEquals(category, validatedCategory);
    }

    //Doesnt Exist
    @Test
    void testThrowNotFoundException_WhenCategoryDoesNotExists() {
        when(categoryRepo.findById(1L)).thenReturn(Optional.empty());
        CategoryValidator validator = new CategoryValidator(null, categoryRepo);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> validator.validateExists(1L));
        assertEquals("Category with id 1 not found", exception.getMessage());
    }

    //Dont throw exception when name isnt duplicated
    @Test
    void testNotThrowException_WhenCategoryNameIsNotDuplicated() {

        when(categoryRepo.findByName("New Category")).thenReturn(Optional.empty());
        CategoryValidator validator = new CategoryValidator(null, categoryRepo);

        assertDoesNotThrow(() -> validator.validateNotDuplicate("New Category"));
    }

    // Throw exveption when name is duplicated and cat is active
    @Test
    void testThrowDuplicateException_WhenCategoryNameDuplicateAndIsActive() {
        Category category = new Category();
        category.setName("Existing Category");
        category.setActive(true);
        when(categoryRepo.findByName("Existing Category")).thenReturn(Optional.of(category));
        CategoryValidator validator = new CategoryValidator(null, categoryRepo);

        DuplicateException exception = assertThrows(
                DuplicateException.class,
                () -> validator.validateNotDuplicate("Existing Category"));
        assertEquals("Category with name Existing Category already exists", exception.getMessage());
    }

    // Throw exveption when name is duplicated and cat is not active
    @Test
    void testThrowDuplicateException_WhenCategoryNameDuplicateButNotActive() {

        Category category = new Category();
        category.setName("Existing Category");
        category.setActive(false);
        when(categoryRepo.findByName("Existing Category")).thenReturn(Optional.of(category));
        CategoryValidator validator = new CategoryValidator(null, categoryRepo);

        DuplicateException exception = assertThrows(
                DuplicateException.class,
                () -> validator.validateNotDuplicate("Existing Category"));
        assertEquals("Category with name Existing Category already exists but is not active", exception.getMessage());
    }

    @Test
    public void shouldReturnNull_WhenCategoryNameDoesNotExist() {
        String name = "Test Category";
        when(categoryRepo.findByName(name)).thenReturn(Optional.empty());

        Category result = categoryValidator.validateNotDuplicate(new Category(), name);

        assertEquals(null, result);
    }

    @Test
    public void shouldThrowDuplicateException_WhenActiveCategoryNameAlreadyExists() {
        Category category = new Category();
        String name = "Test Category";
        category.setActive(true);
        when(categoryRepo.findByName(name)).thenReturn(Optional.of(category));

        assertThrows(DuplicateException.class, () -> categoryValidator.validateNotActiveDuplicate(name));
    }

    @Test
    public void shouldReturnCategory_WhenInactiveCategoryNameAlreadyExists() {
        Category category = new Category();
        String name = "Test Category";
        category.setActive(false);
        when(categoryRepo.findByName(name)).thenReturn(Optional.of(category));

        Category result = categoryValidator.validateNotActiveDuplicate(name);

        assertEquals(category, result);
    }

    @Test
    public void shouldReturnNull_WhenCategoryNameDoesNotExistForInactiveCategories() {
        // Given
        String name = "Test Category";
        when(categoryRepo.findByName(name)).thenReturn(Optional.empty());

        // When
        Category result = categoryValidator.validateNotActiveDuplicate(name);

        // Then
        assertEquals(null, result);
    }
}


