package pl.envelo.meetek.service.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.event.EventResponseRepo;

@AllArgsConstructor
@Service
public class EventResponseService {

    private final EventResponseRepo eventResponseRepo;
}
