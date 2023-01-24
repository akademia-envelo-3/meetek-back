package pl.envelo.meetek.repository.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.group.Section;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepo extends JpaRepository<Section, Long> {

    List<Section> findAllByIsActiveOrderByName(boolean isActive);

    List<Section> findAllOwnedBySectionOwnerParticipantId(long userId);

    Optional<Section> findById(long id);

    @Query(value = """
            SELECT * FROM section
            WHERE section.group_id
            IN (SELECT section_group_id FROM section_joined_users WHERE joined_users_participant_id = ?1)
            ORDER BY name
            """,
            nativeQuery = true)
    List<Section> findAllJoinedSections(long userId);

}
