package pl.envelo.meetek.service.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.comment.RequestComment;
import pl.envelo.meetek.model.request.RequestStatus;
import pl.envelo.meetek.model.category.Category;
import pl.envelo.meetek.model.request.CategoryRequest;
import pl.envelo.meetek.repository.request.CategoryRequestRepo;
import pl.envelo.meetek.service.category.CategoryService;
import pl.envelo.meetek.service.comment.RequestCommentService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryRequestService {

    private final CategoryRequestRepo categoryRequestRepo;
    private final CategoryService categoryService;
    private final RequestCommentService requestCommentService;

    @Transactional(readOnly = true)
    public List<CategoryRequest> getAllNotProcessedRequests() {
        return categoryRequestRepo.findAllByStatus(RequestStatus.NOT_PROCESSED);
    }

    @Transactional(readOnly = true)
    public Optional<CategoryRequest> getCategoryRequestById(long id) {
        return categoryRequestRepo.findById(id);
    }

    @Transactional
    public CategoryRequest createCategoryRequest(CategoryRequest categoryRequest) {
        return categoryRequestRepo.save(categoryRequest);
    }

    @Transactional
    public void replyToRequest(CategoryRequest request) {
        if (request.getStatus() == RequestStatus.REJECTED) {
            RequestComment requestComment = requestCommentService.createRequestComment(request.getComment());
            request.setComment(requestComment);
        } else if (request.getStatus() == RequestStatus.ACCEPTED) {
            if (request.getCategory() == null) {
                categoryService.saveNewCategory(new Category(request.getName(), true));
            } else {
                categoryService.activateCategory(request.getCategory());
            }
        }
        categoryRequestRepo.save(request);
    }

}
