package pl.envelo.meetek.domain.category;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryValidator categoryValidator;
    private final DtoMapperService mapperService;

    @Transactional(readOnly = true)
    public CategoryDto getCategoryById(long categoryId) {
        Category category = categoryValidator.validateExists(categoryId);
        return mapperService.mapToCategoryDto(category);
    }

    @Transactional(readOnly = true)
    public Category getCategoryByName(String name) {
        return categoryValidator.validateNotActiveDuplicate(name);
    }

    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapperService.mapToCategory(categoryDto);
        categoryValidator.validateInput(category);
        categoryValidator.validateNotDuplicate(category.getName());
        category = categoryRepo.save(category);
        return mapperService.mapToCategoryDto(category);
    }

    @Transactional
    public void editCategory(long categoryId, CategoryDto categoryDto) {
        Category category = categoryValidator.validateExists(categoryId);
        Category categoryFromDto = mapperService.mapToCategory(categoryDto);
        categoryValidator.validateInput(categoryFromDto);
        categoryValidator.validateNotDuplicate(category, categoryFromDto.getName());
        updateFields(category, categoryFromDto);
        categoryRepo.save(category);
    }

    @Transactional //TODO - used in CategoryRequestService
    public void activateCategory(Category category) {
        category.setActive(true);
        categoryRepo.save(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepo.findAll(Sort.by("name"));
        return categories.stream()
                .map(mapperService::mapToCategoryDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> getAllActiveCategories() {
        List<Category> categories = categoryRepo.findAllByIsActiveTrueOrderByName();
        return categories.stream()
                .map(mapperService::mapToCategoryDto)
                .toList();
    }

    private void updateFields(Category category, Category categoryFromDto) {
        category.setName(categoryFromDto.getName());
        category.setActive(categoryFromDto.isActive());
    }

}
