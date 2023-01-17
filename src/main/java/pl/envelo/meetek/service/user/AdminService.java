package pl.envelo.meetek.service.user;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.user.AdminRepo;

@AllArgsConstructor
@Service
public class AdminService {

    private final AdminRepo adminRepo;
}
