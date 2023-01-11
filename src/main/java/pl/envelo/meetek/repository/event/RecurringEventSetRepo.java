package pl.envelo.meetek.repository.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.event.RecurringEventSet;


@Repository
public interface RecurringEventSetRepo extends CrudRepository<RecurringEventSet, Long> {
}
