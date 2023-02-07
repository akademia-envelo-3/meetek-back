package pl.envelo.meetek.domain.hashtag;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepo extends JpaRepository<Hashtag, Long> {

    List<Hashtag> findAllByIsActiveTrueOrderByName();

    List<Hashtag> findAll(Sort sort);

}
