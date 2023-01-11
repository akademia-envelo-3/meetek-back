package pl.envelo.meetek.service.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.event.SingleEventRepo;

@AllArgsConstructor
@Service
public class SingleEventService {

    private final SingleEventRepo singleEventRepo;
}
