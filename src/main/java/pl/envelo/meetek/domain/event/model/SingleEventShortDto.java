package pl.envelo.meetek.domain.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.category.CategoryDto;
import pl.envelo.meetek.domain.coordinates.CoordinatesDto;
import pl.envelo.meetek.domain.hashtag.HashtagDto;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SingleEventShortDto {

    private long eventId;
    private Set<HashtagDto> hashtags;
    private String name;
    private LocalDateTime dateTimeFrom;
    private CategoryDto category;
    private CoordinatesDto coordinates;
    private String locationName;
    private boolean isExternal;
    private boolean isPrivate;

}
