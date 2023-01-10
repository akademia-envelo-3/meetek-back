package pl.envelo.meetek.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.Hashtag;
import pl.envelo.meetek.model.user.AppUser;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class Event {

    private Long eventId;
    private Set<Hashtag> hashtags;
    private AppUser owner;
    private String name;
    private String link;
    private String description;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;

}
