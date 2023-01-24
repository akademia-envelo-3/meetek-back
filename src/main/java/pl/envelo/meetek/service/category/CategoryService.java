package pl.envelo.meetek.service.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.category.Category;
import pl.envelo.meetek.repository.category.CategoryRepo;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryService {

    final private CategoryRepo categoryRepo;

    public Optional<Category> getCategoryById(long id) {
        return categoryRepo.findById(id);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepo.findByName(name);
    }

    public Category saveNewCategory(Category category) {
        return categoryRepo.save(category);
    }

    public Category editCategory(long categoryId, Category category) {
        category.setCategoryId(categoryId);
        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAllByOrderByName();
    }

    public List<Category> getAllActiveCategories() {
        return categoryRepo.findAllByIsActiveOrderByName(true);
    }

}
