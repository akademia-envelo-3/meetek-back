package pl.envelo.meetek.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.user.model.StandardUser;

@AllArgsConstructor
@Service
public class StandardUserService {

    private final StandardUserValidator standardUserValidator;

    @Transactional(readOnly = true)
    public StandardUser getStandardUserById(long userId){
        return standardUserValidator.validateExists(userId);
    }

}
