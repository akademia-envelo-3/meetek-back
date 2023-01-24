package pl.envelo.meetek.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.user.Guest;
import pl.envelo.meetek.repository.user.GuestRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GuestService {

    private final GuestRepo guestRepo;

    @Transactional(readOnly = true)
    public Optional<Guest> getGuestById(long guestId) {
        return guestRepo.findById(guestId);
    }

    @Transactional
    public Guest createGuest(Guest guest) {
        return guestRepo.save(guest);
    }

    @Transactional
    public void deleteGuestById(long guestId) {
        guestRepo.deleteById(guestId);
    }

}
