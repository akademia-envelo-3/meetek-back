package pl.envelo.meetek.repository.comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.comment.EventComment;

@Repository
public interface EventCommentRepo extends CrudRepository<EventComment, Long> {

}
