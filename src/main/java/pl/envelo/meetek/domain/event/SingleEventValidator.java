package pl.envelo.meetek.domain.event;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.event.model.SingleEvent;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class SingleEventValidator extends ValidatorService<SingleEvent> {

    private final SingleEventRepo singleEventRepo;

    public SingleEventValidator(Validator validator, SingleEventRepo singleEventRepo) {
        super(validator);
        this.singleEventRepo = singleEventRepo;
    }

    @Override
    public SingleEvent validateExists(long id) {
        Optional<SingleEvent> event = singleEventRepo.findById(id);
        if (event.isEmpty()) {
            throw new NotFoundException("Event with id " + id + " not found");
        }
        return event.get();
    }

    public Integer validateDaysCount(Integer days) {
        if (days == null) {
            return null;
        } else if (days == 0) {
            return 1;
        } else {
            return days;
        }
    }

}
