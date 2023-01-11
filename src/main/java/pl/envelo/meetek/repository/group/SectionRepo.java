package pl.envelo.meetek.repository.group;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.group.Section;

@Repository
public interface SectionRepo extends CrudRepository<Section, Long> {
}
