package pl.envelo.meetek.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.event.RecurringEventSet;


@Repository
public interface RecurringEventSetRepo extends JpaRepository<RecurringEventSet, Long> {
}
