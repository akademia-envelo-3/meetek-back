package pl.envelo.meetek.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.Category;
import pl.envelo.meetek.repository.CategoryRepo;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {

    final private CategoryRepo categoryRepo;

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

}
