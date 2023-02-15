package pl.envelo.meetek.domain.event;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.event.model.EventResponseStatus;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.domain.event.model.TimeStatus;
import pl.envelo.meetek.domain.user.StandardUserValidator;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.exceptions.ArgumentNotValidException;
import pl.envelo.meetek.exceptions.DuplicateException;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class SingleEventValidator extends ValidatorService<SingleEvent> {

    private final SingleEventRepo singleEventRepo;

    private final StandardUserValidator userValidator;

    public SingleEventValidator(Validator validator, SingleEventRepo singleEventRepo, StandardUserValidator userValidator) {
        super(validator);
        this.singleEventRepo = singleEventRepo;
        this.userValidator = userValidator;
    }

    @Override
    public SingleEvent validateExists(long id) {
        Optional<SingleEvent> event = singleEventRepo.findById(id);
        if (event.isEmpty()) {
            throw new NotFoundException("Event with id " + id + " not found");
        }
        return event.get();
    }

    public StandardUser validateOwnerForAdmin(SingleEvent event, long newOwnerId) {
        if (event.getOwner().getParticipantId().equals(newOwnerId)) {
            throw new DuplicateException("User with id " + newOwnerId + " is already the owner of this event");
        }
        return userValidator.validateExists(newOwnerId);
    }

    public TimeStatus validateTimeParameter(String time) {
        Optional<TimeStatus> timeStatus = TimeStatus.findTimeStatus(time.toUpperCase());
        if (timeStatus.isEmpty()) {
            throw new ArgumentNotValidException("Invalid parameter: " + time + ", accepted values: " + TimeStatus.FUTURE + ", " + TimeStatus.PAST);
        }
        return timeStatus.get();
    }

    public EventResponseStatus validateResponseParameter(String response) {
        Optional<EventResponseStatus> responseStatus = EventResponseStatus.findResponseStatus(response);
        if (responseStatus.isEmpty()) {
            throw new ArgumentNotValidException("Invalid parameter: " + response + ", accepted values: " + EventResponseStatus.ACCEPTED + ", " + EventResponseStatus.REJECTED + ", " + EventResponseStatus.UNDECIDED);
        }
        return responseStatus.get();
    }

}
