package pl.envelo.meetek.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.user.StandardUser;
import pl.envelo.meetek.repository.user.StandardUserRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class StandardUserService {

    private final StandardUserRepo standardUserRepo;

    public Optional<StandardUser> getStandardUserById(long id){
        return standardUserRepo.findById(id);
    }
}
