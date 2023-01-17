package pl.envelo.meetek.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.dto.AttachmentDto;
import pl.envelo.meetek.dto.CategoryDto;
import pl.envelo.meetek.dto.CoordinatesDto;
import pl.envelo.meetek.model.Hashtag;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SingleEventGuestDto {

    private long eventId;
    private Set<Hashtag> hashtags;
    private String name;
    private String link;
    private String description;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;
    private CategoryDto category;
    private int numberOfJoinedParticipants;
    private String locationName;
    private CoordinatesDto coordinates;
    private boolean isOnline;
    private int participantsLimit;
    private Set<AttachmentDto> attachments;

}
