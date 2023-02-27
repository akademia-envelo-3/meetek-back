package pl.envelo.meetek.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.category.Category;
import pl.envelo.meetek.domain.category.CategoryDto;
import pl.envelo.meetek.domain.user.model.Admin;
import pl.envelo.meetek.domain.user.model.AdminDto;
import pl.envelo.meetek.utils.DtoMapperService;

@AllArgsConstructor
@Service
public class AdminService {
    private final AdminValidator adminValidator;

    private final DtoMapperService mapperService;

    public Admin getById(long userId) {
        return adminValidator.validateExists(userId);
    }
    

}
