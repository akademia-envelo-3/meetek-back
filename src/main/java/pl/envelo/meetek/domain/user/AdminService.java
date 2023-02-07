package pl.envelo.meetek.domain.user;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.user.model.Admin;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AdminService {

    private final AdminRepo adminRepo;

    public Optional<Admin> getById(long userId){
        return adminRepo.findById(userId);
    }

}
