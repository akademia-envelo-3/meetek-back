package pl.envelo.meetek.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.user.GuestRepo;

@AllArgsConstructor
@Service
public class GuestService {

    private final GuestRepo guestRepo;
}
