package pl.envelo.meetek.service.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.request.RequestBox;
import pl.envelo.meetek.repository.request.RequestBoxRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RequestBoxService {

    private final RequestBoxRepo requestBoxRepo;

    @Transactional(readOnly = true)
    public Optional<RequestBox> getRequestBoxById(long id) {
        return requestBoxRepo.findById(id);
    }

}
