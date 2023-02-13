package pl.envelo.meetek.domain.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.category.CategoryDto;
import pl.envelo.meetek.domain.coordinates.CoordinatesDto;
import pl.envelo.meetek.domain.hashtag.HashtagDto;
import pl.envelo.meetek.domain.user.model.StandardUserDto;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SingleEventLongDto {

    private long eventId;
    private Set<HashtagDto> hashtags;
    private StandardUserDto owner;
    private String name;
    private String link;
    private String description;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;
    private CategoryDto category;
    private String locationName;
    private CoordinatesDto coordinates;
    private boolean isOnline;
    private boolean isExternal;
    private boolean isPrivate;
    private boolean isResponseRequired;
    private int participantsLimit;

}
