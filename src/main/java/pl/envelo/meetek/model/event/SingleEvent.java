package pl.envelo.meetek.model.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.attachment.Attachment;
import pl.envelo.meetek.model.category.Category;
import pl.envelo.meetek.model.coordinates.Coordinates;
import pl.envelo.meetek.model.hashtag.Hashtag;
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
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne
    private Category category;
    @ManyToMany
    private Set<StandardUser> invitedUsers;
    @ManyToMany
    private Map<StandardUser, EventResponse> participants;
    @OneToMany
    private Set<Guest> joinedGuests;
    @OneToMany(mappedBy = "event")
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

    @Override
    public String toString() {
        return super.toString() + " SingleEvent{" +
                "group=" + group +
                ", category=" + category +
                ", invitedUsers=" + invitedUsers +
                ", participants=" + participants +
                ", joinedGuests=" + joinedGuests +
                ", comments=" + comments +
                ", locationName='" + locationName + '\'' +
                ", coordinates=" + coordinates +
                ", isOnline=" + isOnline +
                ", isExternal=" + isExternal +
                ", isPrivate=" + isPrivate +
                ", eventResponseRequired=" + eventResponseRequired +
                ", participantsLimit=" + participantsLimit +
                ", attachments=" + attachments +
                ", surveys=" + surveys +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SingleEvent that = (SingleEvent) o;
        return isOnline == that.isOnline && isExternal == that.isExternal && isPrivate == that.isPrivate && eventResponseRequired == that.eventResponseRequired && participantsLimit == that.participantsLimit && Objects.equals(group, that.group) && Objects.equals(category, that.category) && Objects.equals(invitedUsers, that.invitedUsers) && Objects.equals(participants, that.participants) && Objects.equals(joinedGuests, that.joinedGuests) && Objects.equals(comments, that.comments) && Objects.equals(locationName, that.locationName) && Objects.equals(coordinates, that.coordinates) && Objects.equals(attachments, that.attachments) && Objects.equals(surveys, that.surveys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), group, category, invitedUsers, participants, joinedGuests, comments, locationName, coordinates, isOnline, isExternal, isPrivate, eventResponseRequired, participantsLimit, attachments, surveys);
    }
}
