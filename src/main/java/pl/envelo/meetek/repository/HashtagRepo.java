package pl.envelo.meetek.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.Hashtag;

@Repository
public interface HashtagRepo extends CrudRepository<Hashtag, Long> {
}
