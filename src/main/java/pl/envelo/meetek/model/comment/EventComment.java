package pl.envelo.meetek.model.comment;

import pl.envelo.meetek.model.Attachment;
import pl.envelo.meetek.model.event.Event;

import java.util.Set;

public class EventComment extends Comment {

    private Event event;
    private EventComment replyToComment;
    private Set<Attachment> attachments;
}
