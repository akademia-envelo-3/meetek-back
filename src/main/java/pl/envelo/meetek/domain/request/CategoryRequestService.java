package pl.envelo.meetek.domain.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.comment.model.RequestComment;
import pl.envelo.meetek.domain.category.Category;
import pl.envelo.meetek.domain.user.model.Admin;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.domain.category.CategoryService;
import pl.envelo.meetek.domain.comment.RequestCommentService;

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
                //categoryService.createCategory(new Category(requestBody.getName(), true));
            } else {
                categoryService.activateCategory(categoryRequestToUpdate.getCategory());
            }
        }
        categoryRequestToUpdate.setStatus(requestBody.getStatus());
        categoryRequestRepo.save(categoryRequestToUpdate);
    }

}
