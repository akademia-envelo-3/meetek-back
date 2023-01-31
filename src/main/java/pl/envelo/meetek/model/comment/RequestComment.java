package pl.envelo.meetek.model.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.model.user.Admin;
import pl.envelo.meetek.model.user.AppUser;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "request_comments")
public class RequestComment extends Comment {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Admin commentOwner;

    public RequestComment(Long commentId, LocalDateTime addingDateTime, String comment, Admin commentOwner) {
        super(commentId, addingDateTime, comment);
        this.commentOwner = commentOwner;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
