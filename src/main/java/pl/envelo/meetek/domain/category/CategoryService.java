package pl.envelo.meetek.domain.category;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryService {

    final private CategoryRepo categoryRepo;

    @Transactional(readOnly = true)
    public Optional<Category> getCategoryById(long id) {
        return categoryRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepo.findByName(name);
    }

    @Transactional
    public Category saveNewCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Transactional
    public void editCategory(Category categoryToBeUpdated, Category updatedCategory) {
        categoryToBeUpdated.setName(updatedCategory.getName());
        categoryToBeUpdated.setActive(updatedCategory.isActive());
        categoryRepo.save(categoryToBeUpdated);
    }

    @Transactional
    public void activateCategory(Category category) {
        category.setActive(true);
        categoryRepo.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepo.findAll(Sort.by("name"));
    }

    @Transactional(readOnly = true)
    public List<Category> getAllActiveCategories() {
        return categoryRepo.findAllByIsActiveTrueOrderByName();
    }

}
