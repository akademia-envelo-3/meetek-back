package pl.envelo.meetek.model.comment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.model.attachment.Attachment;
import pl.envelo.meetek.model.event.Event;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "event_comments")
public class EventComment extends Comment {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "replied_comment_id")
    private EventComment replyToComment;
    @OneToMany
    @JoinTable(name = "event_comments_attachments",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id"))
    private Set<Attachment> attachments;

    @Override
    public String toString() {
        return super.toString() + " EventComment{" +
                "event=" + event +
                ", replyToComment=" + replyToComment +
                ", attachments=" + attachments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EventComment that = (EventComment) o;
        return Objects.equals(event, that.event) && Objects.equals(replyToComment, that.replyToComment) && Objects.equals(attachments, that.attachments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), event, replyToComment, attachments);
    }
}
