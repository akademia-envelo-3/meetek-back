package pl.envelo.meetek.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.user.StandardUserRepo;

@AllArgsConstructor
@Service
public class StandardUserService {

    private final StandardUserRepo standardUserRepo;
}
