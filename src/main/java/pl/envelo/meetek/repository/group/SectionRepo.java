package pl.envelo.meetek.repository.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.group.Section;

import java.util.List;

@Repository
public interface SectionRepo extends JpaRepository<Section, Long> {

    List<Section> findAllByIsActiveOrderByName(boolean isActive);


}
