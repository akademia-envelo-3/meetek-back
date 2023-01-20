package pl.envelo.meetek.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.Category;
import pl.envelo.meetek.repository.CategoryRepo;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryService {

    final private CategoryRepo categoryRepo;

    public Optional<Category> getCategoryById(long id) {
        return categoryRepo.findById(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAllByOrderByName();
    }

    public List<Category> getAllActiveCategories() {
        return categoryRepo.findAllByIsActiveOrderByName(true);
    }

}
