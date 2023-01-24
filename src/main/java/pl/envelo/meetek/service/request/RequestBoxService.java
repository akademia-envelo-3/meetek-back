package pl.envelo.meetek.service.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.request.RequestBox;
import pl.envelo.meetek.repository.request.RequestBoxRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RequestBoxService {

    private final RequestBoxRepo requestBoxRepo;

    public Optional<RequestBox> getRequestBoxById(long id) {
        return requestBoxRepo.findById(id);
    }

}
