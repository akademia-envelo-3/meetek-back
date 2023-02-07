package pl.envelo.meetek.service.user;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.user.Admin;
import pl.envelo.meetek.repository.user.AdminRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AdminService {

    private final AdminRepo adminRepo;

    public Optional<Admin> getById(long userId){
        return adminRepo.findById(userId);
    }

}
