package pl.envelo.meetek.integration.request;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.request.CategoryRequestRepo;
import pl.envelo.meetek.domain.request.CategoryRequestService;
import pl.envelo.meetek.domain.request.model.CategoryRequest;
import pl.envelo.meetek.domain.request.model.CategoryRequestDto;
import pl.envelo.meetek.domain.request.model.RequestStatus;
import pl.envelo.meetek.domain.user.StandardUserRepo;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class CategoryRequestServiceIntegrationTest {

    @Autowired
    CategoryRequestService requestService;

    @Autowired
    CategoryRequestRepo requestRepo;

    @Autowired
    StandardUserRepo userRepo;

    @Test
    void getAllNotProcessedRequests_whenExist_Integration(){
        // given
        StandardUser user = userRepo.save(new StandardUser());
        CategoryRequest request = new CategoryRequest(0L, user, RequestStatus.NOT_PROCESSED,"aaaaa", null, null );
        CategoryRequest requestSec = new CategoryRequest(0L, user, RequestStatus.NOT_PROCESSED,"Secaaaaa", null, null );
        CategoryRequest requestAccepted= new CategoryRequest(0L, user, RequestStatus.ACCEPTED,"Accepted", null, null );
        CategoryRequest requestRejected = new CategoryRequest(0L, user, RequestStatus.REJECTED,"Rejected", null, null );

        CategoryRequest request1 = requestRepo.save(request);
        CategoryRequest request2 = requestRepo.save(requestSec);
        CategoryRequest request3 = requestRepo.save(requestAccepted);
        CategoryRequest request4 = requestRepo.save(requestRejected);
        //when
        List<CategoryRequestDto> requestDtoList = requestService.getAllNotProcessedRequests();
        //then
        assertThat(requestDtoList.get(0).getRequestId()).isEqualTo(request1.getRequestId());
        assertThat(requestDtoList.get(1).getRequestId()).isEqualTo(request2.getRequestId());
        assertThat(requestDtoList.size()).isEqualTo(2);
    }

}
