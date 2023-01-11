package pl.envelo.meetek.model.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.comment.Comment;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class CommentNotification extends Notification {

    @OneToOne
    private Comment comment;
}
