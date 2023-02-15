package pl.envelo.meetek.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.domain.comment.model.EventComment;

@Repository
public interface EventCommentRepo extends JpaRepository<EventComment, Long> {

}
