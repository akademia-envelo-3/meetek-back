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
        CategoryRequest request = validateRequest(categoryRequestId);
        CategoryRequest requestFromDto = validateRequestDto(categoryRequestDto);

        if (requestFromDto.getStatus() == RequestStatus.ACCEPTED) {
            acceptRequest(request, requestFromDto);
        } else if (requestFromDto.getStatus() == RequestStatus.REJECTED) {
            rejectRequest(admin, request, requestFromDto);
        }
    }

    private CategoryRequest validateRequest(long categoryRequestId) {
        CategoryRequest request = categoryRequestValidator.validateExists(categoryRequestId);
        categoryRequestValidator.validateNotProcessed(request);
        return request;
    }

    private CategoryRequest validateRequestDto(CategoryRequestDto categoryRequestDto) {
        categoryRequestValidator.validateRequestStatus(categoryRequestDto.getRequestStatus());
        CategoryRequest requestFromDto = mapperService.mapToCategoryRequest(categoryRequestDto);
        categoryRequestValidator.validateRejection(requestFromDto);
        return requestFromDto;
    }

    private void acceptRequest(CategoryRequest request, CategoryRequest requestFromDto) {
        categoryService.createOrActivateCategory(request);
        List<CategoryRequest> requests = getAllNotProcessedRequestsByName(request.getName());
        requests.forEach(categoryRequest -> {
            categoryRequest.setStatus(requestFromDto.getStatus());
            categoryRequestRepo.save(categoryRequest);
        });
    }

    private void rejectRequest(Admin admin, CategoryRequest request, CategoryRequest requestFromDto) {
        request.setComment(requestCommentService.createRequestComment(admin, requestFromDto.getComment().getComment()));
        request.setStatus(requestFromDto.getStatus());
        categoryRequestRepo.save(request);
    }

    private List<CategoryRequest> getAllNotProcessedRequestsByName(String name) {
        return categoryRequestRepo.findAllByStatusAndName(RequestStatus.NOT_PROCESSED, name);
    }

}
