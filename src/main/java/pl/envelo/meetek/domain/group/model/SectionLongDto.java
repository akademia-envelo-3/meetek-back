package pl.envelo.meetek.domain.group.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.user.model.StandardUserShortDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectionLongDto {

    private long groupId;
    private String name;
    private String description;
    private boolean isActive;
    private int membersCount;
    private StandardUserShortDto groupOwner;

}
