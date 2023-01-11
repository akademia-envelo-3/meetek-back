package pl.envelo.meetek.service.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.request.RequestBoxRepo;

@AllArgsConstructor
@Service
public class RequestBoxService {
    
    private final RequestBoxRepo requestBoxRepo;
}
