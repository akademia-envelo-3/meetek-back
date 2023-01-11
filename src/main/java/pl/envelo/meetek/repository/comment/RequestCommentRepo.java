package pl.envelo.meetek.repository.comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.comment.RequestComment;

@Repository
public interface RequestCommentRepo extends CrudRepository<RequestComment, Long> {
}
