package pl.envelo.meetek.repository.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.group.Section;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepo extends JpaRepository<Section, Long> {

    List<Section> findAllByIsActiveOrderByName(boolean isActive);

    List<Section> findAllOwnedBySectionOwnerParticipantId(long userId);

    Optional<Section> findById(long id);

}
