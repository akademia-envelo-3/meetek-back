package pl.envelo.meetek.integration.category;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.category.Category;
import pl.envelo.meetek.domain.category.CategoryDto;
import pl.envelo.meetek.domain.category.CategoryRepo;
import pl.envelo.meetek.domain.category.CategoryService;
import pl.envelo.meetek.exceptions.ArgumentNotValidException;
import pl.envelo.meetek.exceptions.DuplicateException;
import pl.envelo.meetek.exceptions.NotFoundException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class CategoryServiceIntegrationTest {


    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepo categoryRepo;

    @Test
    void createCategory_whenDataValid_integration(){
        // given
        CategoryDto categoryDto = new CategoryDto(0L,"test");
        // when

        CategoryDto categoryDtoCreated = categoryService.createCategory(categoryDto);
        // then
        assertEquals(categoryDto.getName(), categoryDtoCreated.getName());
        assertThat(categoryDtoCreated.isActive()).isTrue();
    }

    @Test
    void createCategory_whenDataInvalid_integration(){
        // given
        CategoryDto categoryDto = new CategoryDto(0L,"t");
        // when

        // then
        assertThrows(ArgumentNotValidException.class,() -> categoryService.createCategory(categoryDto));
    }

    @Test
    void createCategory_whenCategoryExist_integration(){
        // given
        categoryRepo.save(new Category(0L,"test"));
        CategoryDto categoryDto = new CategoryDto(0L,"test");
        // when

        // then
        assertThrows(DuplicateException.class,() -> categoryService.createCategory(categoryDto));
    }

    @Test
    void editCategory_whenCategoryDataValid_integration(){
        // given
        Category category = categoryRepo
                .save(new Category(0L,"test"));
        CategoryDto categoryDto = new CategoryDto(0L,"testNowy");
        // when
        categoryService.editCategory(category.getCategoryId(), categoryDto);
        // then
        assertThat(categoryService.getCategoryById(category.getCategoryId()).getName()).isEqualTo("testNowy");
    }
    @Test
    void editCategory_whenCategoryNotFound_integration() {
        // given
        CategoryDto categoryDto = new CategoryDto(0L, "test");
        // when

        // then
        assertThrows(NotFoundException.class, () -> categoryService.editCategory(1L, categoryDto));
    }

    @Test
    void editCategory_whenCategoryNameIsEqualToInput_integration() {
        // given
        Category category = categoryRepo
                .save(new Category(0L,"test"));
        Category categoryToEdit = categoryRepo
                .save(new Category(0L,"test1"));
        CategoryDto categoryDto = new CategoryDto(0L, "test");
        // when

        // then
        assertThrows(DuplicateException.class, () -> categoryService.editCategory(categoryToEdit.getCategoryId(), categoryDto));
    }

    @Test
    void getAllCategories_WhenExists_integration(){
        // given
        Category categoryActive = categoryRepo
                .save(new Category(0L,"test", true));
        Category categoryNotActive = categoryRepo
                .save(new Category(0L,"test2", false));

        // when
        List<CategoryDto> categoryList = categoryService.getAllCategories();
        // then
        assertThat(categoryList.get(0).getName()).isEqualTo(categoryActive.getName());
        assertThat(categoryList.get(1).getName()).isEqualTo(categoryNotActive.getName());
        assertThat(categoryList.size()).isEqualTo(2);
    }

    @Test
    void getAllActiveCategories_WhenExists_integration(){
        // given
        Category categoryActive = categoryRepo
                .save(new Category(0L,"test", true));
        Category categoryNotActive = categoryRepo
                .save(new Category(0L,"test2", false));
        // when
        List<CategoryDto> categoryList = categoryService.getAllActiveCategories();
        // then
        assertThat(categoryList.get(0).getName()).isEqualTo(categoryActive.getName());
        assertThat(categoryList.size()).isEqualTo(1);
    }



}
