package pl.envelo.meetek.domain.user;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.user.model.Admin;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class AdminValidator extends ValidatorService<Admin> {

    private final AdminRepo adminRepo;

    public AdminValidator(Validator validator, AdminRepo adminRepo) {
        super(validator);
        this.adminRepo = adminRepo;
    }

    @Override
    public Admin validateExists(long id) {
        Optional<Admin> admin = adminRepo.findById(id);
        if (admin.isEmpty()) {
            throw new NotFoundException("Admin with id " + id + " not found");
        }
        return admin.get();
    }

}
