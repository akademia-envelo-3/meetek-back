package pl.envelo.meetek.model.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.user.AppUser;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class Comment {

    private Long commentId;
    private AppUser commentOwner;
    private LocalDateTime addingDateTime;
    private String comment;

}
