package pl.envelo.meetek.dto.event;

import pl.envelo.meetek.dto.CategoryDto;
import pl.envelo.meetek.dto.HashtagDto;

import java.time.LocalDateTime;
import java.util.Set;

public class SingleEventShortDto {

        private long eventId;
        private Set<HashtagDto> hashtags;
        private String name;
        private LocalDateTime dateTimeFrom;
        private CategoryDto category;
        private boolean isPrivate;

}
