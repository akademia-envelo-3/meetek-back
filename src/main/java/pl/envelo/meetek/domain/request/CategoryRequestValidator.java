package pl.envelo.meetek.domain.request;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.request.model.CategoryRequest;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class CategoryRequestValidator extends ValidatorService<CategoryRequest> {

    private final CategoryRequestRepo categoryRequestRepo;

    public CategoryRequestValidator(Validator validator, CategoryRequestRepo categoryRequestRepo) {
        super(validator);
        this.categoryRequestRepo = categoryRequestRepo;
    }

    public CategoryRequest validateExists(long id) {
        Optional<CategoryRequest> categoryRequest = categoryRequestRepo.findById(id);
        if (categoryRequest.isEmpty()) {
            throw new NotFoundException("Category request with id " + id);
        }
        return categoryRequest.get();
    }

}
