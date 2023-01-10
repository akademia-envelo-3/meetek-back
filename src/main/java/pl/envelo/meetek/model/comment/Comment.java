package pl.envelo.meetek.model.comment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.user.AppUser;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public abstract class Comment {

    private Long commentId;
    @ManyToOne
    private AppUser commentOwner;
    private LocalDateTime addingDateTime;
    private String comment;

}
