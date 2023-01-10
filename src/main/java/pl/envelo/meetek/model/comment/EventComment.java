package pl.envelo.meetek.model.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.Attachment;
import pl.envelo.meetek.model.event.Event;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class EventComment extends Comment {

    @ManyToOne
    private Event event;
    @ManyToOne
    private EventComment replyToComment;
    @OneToMany
    private Set<Attachment> attachments;

}
