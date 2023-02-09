package pl.envelo.meetek.domain.user;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.user.model.Guest;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class GuestValidator extends ValidatorService<Guest> {

    private final GuestRepo guestRepo;

    public GuestValidator(Validator validator, GuestRepo guestRepo) {
        super(validator);
        this.guestRepo = guestRepo;
    }

    @Override
    public Guest validateExists(long id) {
        Optional<Guest> guest = guestRepo.findById(id);
        if (guest.isEmpty()) {
            throw new NotFoundException("Guest with id " + id + " not found");
        }
        return guest.get();
    }

}
