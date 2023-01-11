package pl.envelo.meetek.service.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.comment.EventCommentRepo;

@AllArgsConstructor
@Service
public class EventCommentService {

    private final EventCommentRepo eventCommentRepo;
}
