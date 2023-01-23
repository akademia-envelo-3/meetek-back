package pl.envelo.meetek.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.user.Guest;
import pl.envelo.meetek.repository.user.GuestRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GuestService {

    private final GuestRepo guestRepo;

    public Optional<Guest> getGuestById(long guestId) {
        return guestRepo.findById(guestId);
    }

    public Guest createGuest(Guest guest) {
        return guestRepo.save(guest);
    }

    public void deleteGuestById(long guestId) {
        guestRepo.deleteById(guestId);
    }

}
