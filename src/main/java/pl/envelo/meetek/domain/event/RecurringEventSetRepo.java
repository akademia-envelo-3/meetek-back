package pl.envelo.meetek.domain.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.domain.event.model.RecurringEventSet;

@Repository
public interface RecurringEventSetRepo extends JpaRepository<RecurringEventSet, Long> {
}
