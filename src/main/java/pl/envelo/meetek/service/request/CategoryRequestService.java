package pl.envelo.meetek.service.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.request.CategoryRequest;
import pl.envelo.meetek.repository.request.CategoryRequestRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryRequestService {

    private final CategoryRequestRepo categoryRequestRepo;

    @Transactional(readOnly = true)
    public Optional<CategoryRequest> getCategoryRequestById(long id) {
        return categoryRequestRepo.findById(id);
    }

    @Transactional
    public CategoryRequest createCategoryRequest(CategoryRequest categoryRequest) {
        return categoryRequestRepo.save(categoryRequest);
    }

}
