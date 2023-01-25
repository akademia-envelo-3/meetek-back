package pl.envelo.meetek.model.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Setter
@Entity
@Table(name = "events")
public class SingleEvent extends Event {

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToMany
    @JoinTable(name = "events_x_invited_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<StandardUser> invitedUsers;
    @ManyToMany
    @JoinTable(name = "events_x_users_responses",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "response_id"))
    @MapKeyJoinColumn(name = "user_id")
    private Map<StandardUser, EventResponse> participants;
    @OneToMany
    @JoinTable(name = "events_x_joined_guests",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id"))
    private Set<Guest> joinedGuests;
    @OneToMany(mappedBy = "event")
    private Set<EventComment> comments;
    private String locationName;
    @ManyToOne
    @JoinColumn(name = "coordinate_id")
    private Coordinates coordinates;
    private boolean isOnline;
    private boolean isExternal;
    private boolean isPrivate;
    private boolean isResponseRequired;
    private int participantsLimit;
    @OneToMany
    @JoinTable(name = "events_x_attachments",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id"))
    private Set<Attachment> attachments;
    @OneToMany
    @JoinTable(name = "events_x_surveys",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "survey_id"))
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
        this.isResponseRequired = eventResponseRequired;
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
                ", eventResponseRequired=" + isResponseRequired +
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
        return (isOnline == that.isOnline && isExternal == that.isExternal && isPrivate == that.isPrivate && isResponseRequired == that.isResponseRequired && participantsLimit == that.participantsLimit && Objects.equals(group, that.group) && Objects.equals(category, that.category) && Objects.equals(locationName, that.locationName) && Objects.equals(coordinates, that.coordinates) && super.equals(o));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
