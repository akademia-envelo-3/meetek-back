package pl.envelo.meetek.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.user.model.Admin;

@AllArgsConstructor
@Service
public class AdminService {
    private final AdminValidator adminValidator;

    public Admin getById(long userId) {
        return adminValidator.validateExists(userId);
    }

}
