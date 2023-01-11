package pl.envelo.meetek.model.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.Attachment;
import pl.envelo.meetek.model.Category;
import pl.envelo.meetek.model.Coordinates;
import pl.envelo.meetek.model.Hashtag;
import pl.envelo.meetek.model.comment.EventComment;
import pl.envelo.meetek.model.group.Group;
import pl.envelo.meetek.model.survey.Survey;
import pl.envelo.meetek.model.user.AppUser;
import pl.envelo.meetek.model.user.Guest;
import pl.envelo.meetek.model.user.StandardUser;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class SingleEvent extends Event {

    @ManyToOne
    private Group group;
    @ManyToOne
    private Category category;
    @ManyToMany
    private Set<StandardUser> invitedUsers;

    //private Map<StandardUser, EventResponse> participants;
    @OneToMany
    private Set<Guest> joinedGuests;
    @OneToMany
    private Set<EventComment> comments;
    private String locationName;
    @ManyToOne
    private Coordinates coordinates;
    private boolean isOnline;
    private boolean isExternal;
    private boolean isPrivate;
    private boolean eventResponseRequired;
    private int participantsLimit;
    @OneToMany
    private Set<Attachment> attachments;
    @OneToMany
    private Set<Survey> surveys;


    public SingleEvent(Long eventId, Set<Hashtag> hashtags, AppUser owner, String name, String link, String description, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo, Group group, Category category, Set<EventComment> comments, String locationName, Coordinates coordinates, boolean isOnline, boolean isExternal, boolean isPrivate, boolean eventResponseRequired, int participantsLimit, Set<Attachment> attachments) {
        super(eventId, hashtags, owner, name, link, description, dateTimeFrom, dateTimeTo);
        this.group = group;
        this.category = category;
        this.comments = comments;
        this.locationName = locationName;
        this.coordinates = coordinates;
        this.isOnline = isOnline;
        this.isExternal = isExternal;
        this.isPrivate = isPrivate;
        this.eventResponseRequired = eventResponseRequired;
        this.participantsLimit = participantsLimit;
        this.attachments = attachments;
    }
}
