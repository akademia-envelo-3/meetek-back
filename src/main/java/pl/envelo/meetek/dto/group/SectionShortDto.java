package pl.envelo.meetek.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectionShortDto {

    private long groupId;
    private String name;
    private boolean isActive;

}
