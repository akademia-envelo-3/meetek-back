package pl.envelo.meetek.domain.comment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.user.model.AdminDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCommentDto {

    private long commentId;
    private AdminDto commentOwner;
    private LocalDateTime addingDateTime;
    private String comment;

}
