package pl.envelo.meetek.service.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.comment.RequestComment;
import pl.envelo.meetek.model.request.RequestStatus;
import pl.envelo.meetek.model.category.Category;
import pl.envelo.meetek.model.request.CategoryRequest;
import pl.envelo.meetek.model.user.Admin;
import pl.envelo.meetek.model.user.StandardUser;
import pl.envelo.meetek.repository.request.CategoryRequestRepo;
import pl.envelo.meetek.service.category.CategoryService;
import pl.envelo.meetek.service.comment.RequestCommentService;

import java.time.LocalDateTime;
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
    public CategoryRequest createCategoryRequest(StandardUser standardUser, CategoryRequest categoryRequest) {
        categoryRequest.setRequester(standardUser);
        categoryRequest.setStatus(RequestStatus.NOT_PROCESSED);
        return categoryRequestRepo.save(categoryRequest);
    }

    @Transactional
    public void replyToRequest(Admin admin, CategoryRequest categoryRequestToUpdate ,CategoryRequest requestBody) {
        if (requestBody.getStatus() == RequestStatus.REJECTED) {
            RequestComment requestComment = requestCommentService.createRequestComment(requestBody.getComment());
            requestComment.setCommentOwner(admin);
            requestComment.setAddingDateTime(LocalDateTime.now());
            categoryRequestToUpdate.setComment(requestComment);
        } else if (requestBody.getStatus() == RequestStatus.ACCEPTED) {
            if (categoryRequestToUpdate.getCategory() == null) {
                categoryService.saveNewCategory(new Category(requestBody.getName(), true));
            } else {
                categoryService.activateCategory(categoryRequestToUpdate.getCategory());
            }
        }
        categoryRequestToUpdate.setStatus(requestBody.getStatus());
        categoryRequestRepo.save(categoryRequestToUpdate);
    }

}
