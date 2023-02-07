package pl.envelo.meetek.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.domain.comment.model.RequestComment;

@Repository
public interface RequestCommentRepo extends JpaRepository<RequestComment, Long> {
}
