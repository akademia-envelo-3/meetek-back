package pl.envelo.meetek.domain.comment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.attachment.AttachmentDto;
import pl.envelo.meetek.domain.user.model.StandardUserShortDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventCommentDto {

    private long commentId;
    private StandardUserShortDto commentOwner;
    private LocalDateTime addingDateTime;
    private String comment;
    private long eventId;
    private EventCommentDto replyToComment;
    private List<AttachmentDto> attachments;

}
