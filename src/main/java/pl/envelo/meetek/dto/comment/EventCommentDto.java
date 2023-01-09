package pl.envelo.meetek.dto.comment;

import pl.envelo.meetek.dto.AttachmentDto;
import pl.envelo.meetek.dto.user.StandardUserShortDto;

import java.time.LocalDateTime;
import java.util.Set;

public class EventCommentDto {

    private long commentId;
    private StandardUserShortDto commentOwner;
    private LocalDateTime addingDateTime;
    private String comment;
    private long eventId;
    private EventCommentDto replyToComment;
    private Set<AttachmentDto> attachments;

}
