package pl.envelo.meetek.service.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.event.RecurringEventSet;
import pl.envelo.meetek.repository.event.RecurringEventSetRepo;

@AllArgsConstructor
@Service
public class RecurringEventSetService {

    private final RecurringEventSetRepo recurringEventSetRepo;

    @Transactional
    public RecurringEventSet createRecurringEventSet(RecurringEventSet recurringEventSet) {
        return recurringEventSetRepo.save(recurringEventSet);
    }

}
