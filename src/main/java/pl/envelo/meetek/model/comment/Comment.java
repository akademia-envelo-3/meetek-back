package pl.envelo.meetek.model.comment;

import pl.envelo.meetek.model.user.AppUser;

import java.time.LocalDateTime;


public abstract class Comment {

    private Long commentId;
    private AppUser commentOwner;
    private LocalDateTime addingDateTime;
    private String comment;
}
