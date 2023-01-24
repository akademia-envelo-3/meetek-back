package pl.envelo.meetek.service.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.category.Category;
import pl.envelo.meetek.repository.category.CategoryRepo;

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
    public Category editCategory(long categoryId, Category category) {
        category.setCategoryId(categoryId);
        return categoryRepo.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepo.findAllByOrderByName();
    }

    @Transactional(readOnly = true)
    public List<Category> getAllActiveCategories() {
        return categoryRepo.findAllByIsActiveOrderByName(true);
    }

}
