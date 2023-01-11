package pl.envelo.meetek.service.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.request.RequestStatusRepo;

@AllArgsConstructor
@Service
public class RequestStatusService {

    private final RequestStatusRepo requestStatusRepo;
}
