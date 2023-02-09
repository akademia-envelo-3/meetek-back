package pl.envelo.meetek.domain.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.category.Category;
import pl.envelo.meetek.domain.request.model.CategoryRequest;
import pl.envelo.meetek.domain.request.model.CategoryRequestCreateDto;
import pl.envelo.meetek.domain.request.model.CategoryRequestDto;
import pl.envelo.meetek.domain.request.model.RequestStatus;
import pl.envelo.meetek.domain.user.model.Admin;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.domain.category.CategoryService;
import pl.envelo.meetek.domain.comment.RequestCommentService;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryRequestService {

    private final CategoryRequestRepo categoryRequestRepo;
    private final CategoryService categoryService;
    private final RequestCommentService requestCommentService;
    private final CategoryRequestValidator categoryRequestValidator;
    private final DtoMapperService mapperService;

    @Transactional(readOnly = true)
    public List<CategoryRequestDto> getAllNotProcessedRequests() {
        List<CategoryRequest> categoryRequests = categoryRequestRepo.findAllByStatus(RequestStatus.NOT_PROCESSED);
        return categoryRequests.stream()
                .map(mapperService::mapToCategoryRequestDto)
                .toList();
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

    @Transactional
    public void replyToRequest(long categoryRequestId, Admin admin, CategoryRequestDto categoryRequestDto) {
        CategoryRequest request = categoryRequestValidator.validateExists(categoryRequestId);
        categoryRequestValidator.validateNotProcessed(request);
        categoryRequestValidator.validateRequestStatus(categoryRequestDto.getRequestStatus());
        CategoryRequest requestFromDto = mapperService.mapToCategoryRequest(categoryRequestDto);
        categoryRequestValidator.validateRejection(requestFromDto);

        if (requestFromDto.getStatus() == RequestStatus.REJECTED) {
            request.setComment(requestCommentService.createRequestComment(admin, requestFromDto.getComment().getComment()));
        } else if (requestFromDto.getStatus() == RequestStatus.ACCEPTED) {
            if (request.getCategory() != null) {
                categoryService.activateCategory(request.getCategory());
            } else {
                categoryService.createOrActivateCategory(request.getName());
            }
        }

        request.setStatus(requestFromDto.getStatus());
        categoryRequestRepo.save(request);
    }

}
