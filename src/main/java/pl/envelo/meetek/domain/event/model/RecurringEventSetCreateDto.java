package pl.envelo.meetek.domain.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.attachment.AttachmentDto;
import pl.envelo.meetek.domain.category.CategoryDto;
import pl.envelo.meetek.domain.coordinates.CoordinatesDto;
import pl.envelo.meetek.domain.hashtag.HashtagDto;
import pl.envelo.meetek.domain.survey.model.SurveyCreateDto;
import pl.envelo.meetek.domain.user.model.StandardUserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecurringEventSetCreateDto {

    private Integer eventFrequency;
    private Integer recursiveCount;

    private Set<HashtagDto> hashtags;
    private StandardUserDto owner;
    private String name;
    private String link;
    private String description;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;
    private CategoryDto category;
    private Set<StandardUserDto> invitedUsers;
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
