package pl.envelo.meetek.repository.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.group.Section;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepo extends JpaRepository<Section, Long> {

    List<Section> findAllByIsActiveTrueOrderByName();

    @Query(value = """
            SELECT * FROM sections
            WHERE owner_id = ?1
            ORDER BY name
            """,
            nativeQuery = true)
     List<Section> findAllOwnedSections(long userId);

    Optional<Section> findById(long id);

    @Query(value = """
            SELECT * FROM sections
            WHERE group_id
            IN (SELECT section_id FROM SECTIONS_X_JOINED_USERS WHERE user_id = ?1)
            ORDER BY name
            """,
            nativeQuery = true)
    List<Section> findAllJoinedSections(long userId);

}
