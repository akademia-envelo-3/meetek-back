package pl.envelo.meetek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.Hashtag;

@Repository
public interface HashtagRepo extends JpaRepository<Hashtag, Long> {
}
