package pl.envelo.meetek.model.event;

import pl.envelo.meetek.model.Attachment;
import pl.envelo.meetek.model.Category;
import pl.envelo.meetek.model.Coordinates;
import pl.envelo.meetek.model.comment.EventComment;
import pl.envelo.meetek.model.group.Group;
import pl.envelo.meetek.model.survey.Survey;
import pl.envelo.meetek.model.user.Guest;
import pl.envelo.meetek.model.user.StandardUser;

import java.util.Map;
import java.util.Set;

public class SingleEvent extends Event {

    private Group group;
    private Category category;
    private Set<StandardUser> invitedUsers;
    private Map<StandardUser, EventResponse> participants;
    private Set<Guest> joinedGuests;
    private Set<EventComment> comments;
    private String locationName;
    private Coordinates coordinates;
    private boolean isOnline;
    private boolean isExternal;
    private boolean isPrivate;
    private boolean eventResponseRequired;
    private int participantsLimit;
    private Set<Attachment> attachments;
    private Set<Survey> surveys;

}
