package pl.envelo.meetek.domain.comment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long commentId;

    private LocalDateTime addingDateTime;

    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    @Size(min = 10, max = 2000, message = "Field must be between {min} and {max} characters")
    private String comment;

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", addingDateTime=" + addingDateTime +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(commentId, comment1.commentId) && Objects.equals(addingDateTime, comment1.addingDateTime) && Objects.equals(comment, comment1.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, addingDateTime, comment);
    }
}
