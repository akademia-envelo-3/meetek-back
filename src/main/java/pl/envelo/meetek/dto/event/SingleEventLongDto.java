package pl.envelo.meetek.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.dto.AttachmentDto;
import pl.envelo.meetek.dto.CategoryDto;
import pl.envelo.meetek.dto.CoordinatesDto;
import pl.envelo.meetek.dto.comment.EventCommentDto;
import pl.envelo.meetek.dto.group.SectionLongDto;
import pl.envelo.meetek.dto.survey.SurveyDto;
import pl.envelo.meetek.dto.user.GuestDto;
import pl.envelo.meetek.dto.user.StandardUserShortDto;
import pl.envelo.meetek.model.Hashtag;
import pl.envelo.meetek.model.event.EventResponse;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SingleEventLongDto {

    private long eventId;
    private Set<Hashtag> hashtags;
    private StandardUserShortDto owner;
    private String name;
    private String link;
    private String description;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;
    private SectionLongDto group;
    private CategoryDto category;
    private Set<StandardUserShortDto> invitedUsers;
    private Map<StandardUserShortDto, EventResponse> participants;
    private Set<GuestDto> joinedGuests;
    private Set<EventCommentDto> comments;
    private String locationName;
    private CoordinatesDto coordinates;
    private boolean isOnline;
    private boolean isExternal;
    private boolean isPrivate;
    private boolean eventResponseRequired;
    private int participantsLimit;
    private Set<AttachmentDto> attachments;
    private Set<SurveyDto> surveys;

}
