package pl.envelo.meetek.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.dto.attachment.AttachmentDto;
import pl.envelo.meetek.dto.category.CategoryDto;
import pl.envelo.meetek.dto.coordinates.CoordinatesDto;
import pl.envelo.meetek.dto.hashtag.HashtagDto;
import pl.envelo.meetek.dto.survey.SurveyCreateDto;
import pl.envelo.meetek.dto.user.StandardUserShortDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SingleEventCreateDto {

    private Set<HashtagDto> hashtags;
    private StandardUserShortDto owner;
    private String name;
    private String link;
    private String description;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;
    private CategoryDto category;
    private Set<StandardUserShortDto> invitedUsers;
    private String locationName;
    private CoordinatesDto coordinates;
    private Boolean isOnline;
    private Boolean isExternal;
    private Boolean isPrivate;
    private Boolean isResponseRequired;
    private Integer participantsLimit;
    private List<AttachmentDto> attachments;
    private Set<SurveyCreateDto> surveys;

}
