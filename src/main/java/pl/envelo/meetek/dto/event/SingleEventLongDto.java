package pl.envelo.meetek.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.dto.attachment.AttachmentDto;
import pl.envelo.meetek.dto.category.CategoryDto;
import pl.envelo.meetek.dto.coordinates.CoordinatesDto;
import pl.envelo.meetek.dto.comment.EventCommentDto;
import pl.envelo.meetek.dto.survey.SurveyDto;
import pl.envelo.meetek.dto.user.GuestDto;
import pl.envelo.meetek.dto.user.StandardUserShortDto;
import pl.envelo.meetek.model.hashtag.Hashtag;
import pl.envelo.meetek.model.event.EventResponse;

import java.time.LocalDateTime;
import java.util.List;
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
    private boolean isResponseRequired;
    private int participantsLimit;
    private List<AttachmentDto> attachments;
    private Set<SurveyDto> surveys;

}
