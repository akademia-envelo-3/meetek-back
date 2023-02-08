package pl.envelo.meetek.domain.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.category.Category;
import pl.envelo.meetek.domain.comment.model.RequestComment;
import pl.envelo.meetek.domain.request.model.CategoryRequest;
import pl.envelo.meetek.domain.request.model.CategoryRequestCreateDto;
import pl.envelo.meetek.domain.request.model.CategoryRequestDto;
import pl.envelo.meetek.domain.request.model.RequestStatus;
import pl.envelo.meetek.domain.user.model.Admin;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.domain.category.CategoryService;
import pl.envelo.meetek.domain.comment.RequestCommentService;
import pl.envelo.meetek.utils.DtoMapperService;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoryRequestService {

    private final CategoryRequestRepo categoryRequestRepo;
    private final CategoryService categoryService;
    private final RequestCommentService requestCommentService;
    private final CategoryRequestValidator categoryRequestValidator;
    private final DtoMapperService mapperService;

    @Transactional(readOnly = true) //TODO - used in AdminController
    public List<CategoryRequest> getAllNotProcessedRequests() {
        return categoryRequestRepo.findAllByStatus(RequestStatus.NOT_PROCESSED);
    }

    @Transactional(readOnly = true)
    public CategoryRequestDto getCategoryRequestById(long id) {
        CategoryRequest categoryRequest = categoryRequestValidator.validateExists(id);
        return mapperService.mapToCategoryRequestDto(categoryRequest);
    }

    @Transactional
    public CategoryRequestDto createCategoryRequest(StandardUser standardUser, CategoryRequestCreateDto categoryRequestDto) {
        Category category = categoryService.getCategoryByName(categoryRequestDto.getName());
        CategoryRequest categoryRequest = mapperService.mapToCategoryRequest(categoryRequestDto);
        categoryRequest.setCategory(category);
        categoryRequest.setRequester(standardUser);
        categoryRequest.setStatus(RequestStatus.NOT_PROCESSED);
        categoryRequestValidator.validateInput(categoryRequest);
        categoryRequest = categoryRequestRepo.save(categoryRequest);
        return mapperService.mapToCategoryRequestDto(categoryRequest);
    }

    @Transactional //TODO - used in AdminController
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
