package pl.envelo.meetek.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.util.Optional;

@AllArgsConstructor
@Service
public class StandardUserService {

    private final StandardUserRepo standardUserRepo;

    @Transactional(readOnly = true)
    public Optional<StandardUser> getStandardUserById(long id){
        return standardUserRepo.findById(id);
    }

}
