package pl.envelo.meetek.model.comment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.user.AppUser;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public abstract class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long commentId;
    @ManyToOne
    private AppUser commentOwner;
    private LocalDateTime addingDateTime;
    private String comment;

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentOwner=" + commentOwner +
                ", addingDateTime=" + addingDateTime +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(commentId, comment1.commentId) && Objects.equals(commentOwner, comment1.commentOwner) && Objects.equals(addingDateTime, comment1.addingDateTime) && Objects.equals(comment, comment1.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, commentOwner, addingDateTime, comment);
    }
}
