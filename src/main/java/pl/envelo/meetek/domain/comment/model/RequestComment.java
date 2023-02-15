package pl.envelo.meetek.domain.comment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.user.model.Admin;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "request_comments")
public class RequestComment extends Comment {

    @NotNull(message = "Field must not be null")
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
