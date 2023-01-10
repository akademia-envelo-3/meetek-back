package pl.envelo.meetek.model.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.comment.Comment;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentNotification extends Notification {

    private Comment comment;
}
