package pl.envelo.meetek.domain.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.event.model.RecurringEventSet;

@AllArgsConstructor
@Service
public class RecurringEventSetService {

    private final RecurringEventSetRepo recurringEventSetRepo;

    @Transactional
    public RecurringEventSet createRecurringEventSet(RecurringEventSet recurringEventSet) {
        return recurringEventSetRepo.save(recurringEventSet);
    }

}
