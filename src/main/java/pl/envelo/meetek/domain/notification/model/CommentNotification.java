package pl.envelo.meetek.domain.notification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.comment.model.Comment;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notifications_comment")
public class CommentNotification extends Notification {

    @OneToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Override
    public String toString() {
        return super.toString() + " CommentNotification{" +
                "comment=" + comment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentNotification that = (CommentNotification) o;
        return Objects.equals(comment, that.comment) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
