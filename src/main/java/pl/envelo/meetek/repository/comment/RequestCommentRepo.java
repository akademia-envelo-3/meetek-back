package pl.envelo.meetek.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.comment.RequestComment;

@Repository
public interface RequestCommentRepo extends JpaRepository<RequestComment, Long> {
}
