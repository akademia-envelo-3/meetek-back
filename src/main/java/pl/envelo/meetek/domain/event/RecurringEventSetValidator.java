package pl.envelo.meetek.domain.event;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.event.model.RecurringEventSet;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class RecurringEventSetValidator extends ValidatorService<RecurringEventSet> {

    private final RecurringEventSetRepo recurringEventSetRepo;

    public RecurringEventSetValidator(Validator validator, RecurringEventSetRepo recurringEventSetRepo) {
        super(validator);
        this.recurringEventSetRepo = recurringEventSetRepo;
    }

    @Override
    public RecurringEventSet validateExists(long id) {
        Optional<RecurringEventSet> eventSet = recurringEventSetRepo.findById(id);
        if (eventSet.isEmpty()) {
            throw new NotFoundException("Recurring event set with id " + id + " not found");
        }
        return eventSet.get();
    }

}
