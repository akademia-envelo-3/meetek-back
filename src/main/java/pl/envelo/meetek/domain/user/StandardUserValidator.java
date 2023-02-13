package pl.envelo.meetek.domain.user;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class StandardUserValidator extends ValidatorService<StandardUser> {

    private final StandardUserRepo standardUserRepo;

    public StandardUserValidator(Validator validator, StandardUserRepo standardUserRepo) {
        super(validator);
        this.standardUserRepo = standardUserRepo;
    }

    @Override
    public StandardUser validateExists(long id) {
        Optional<StandardUser> user = standardUserRepo.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        return user.get();
    }

}
