package pl.envelo.meetek.model.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.Attachment;
import pl.envelo.meetek.model.event.Event;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EventComment extends Comment {

    private Event event;
    private EventComment replyToComment;
    private Set<Attachment> attachments;
}
