package pl.envelo.meetek.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.user.model.Guest;
import pl.envelo.meetek.domain.user.model.GuestDto;
import pl.envelo.meetek.utils.DtoMapperService;

@AllArgsConstructor
@Service
public class GuestService {

    private final GuestRepo guestRepo;
    private final GuestValidator guestValidator;
    private final DtoMapperService mapperService;

    @Transactional(readOnly = true)
    public GuestDto getGuestById(long guestId) {
        Guest guest = guestValidator.validateExists(guestId);
        return mapperService.mapToGuestDto(guest);
    }

    @Transactional
    public GuestDto createGuest(GuestDto guestDto) {
        Guest guest = mapperService.mapToGuest(guestDto);
        guestValidator.validateInput(guest);
        guest = guestRepo.save(guest);
        return mapperService.mapToGuestDto(guest);
    }

    @Transactional
    public void deleteGuestById(long guestId) {
        guestRepo.deleteById(guestId);
    }

}
