package pl.envelo.meetek.domain.category;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.exceptions.DuplicateException;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class CategoryValidator extends ValidatorService<Category> {

    private final CategoryRepo categoryRepo;

    public CategoryValidator(Validator validator, CategoryRepo categoryRepo) {
        super(validator);
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category validateExists(long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isEmpty()) {
            throw new NotFoundException("Category with id " + id + " not found");
        }
        return category.get();
    }

    public void validateNotDuplicate(String name) {
        Optional<Category> category = categoryRepo.findByName(name);
        if (category.isPresent()) {
            if (category.get().isActive()) {
                throw new DuplicateException("Category with name " + name + " already exists");
            } else {
                throw new DuplicateException("Category with name " + name + " already exists but is not active");
            }
        }
    }

    public Category validateNotDuplicate(Category category, String name) {
        Optional<Category> categoryFromDto = categoryRepo.findByName(name);
        if (categoryFromDto.isPresent() && !category.getName().equals(name)) {
            throw new DuplicateException("Category with name " + name + " already exists");
        }
        return null;
    }

    public Category validateNotActiveDuplicate(String name) {
        Optional<Category> category = categoryRepo.findByName(name);
        if (category.isPresent()) {
            if (category.get().isActive()) {
                throw new DuplicateException("Category with name " + name + " already exists");
            } else {
                return category.get();
            }
        }
        return null;
    }

}
