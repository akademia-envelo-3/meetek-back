package pl.envelo.meetek.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardUserDto {

    private long participantId;
    private String firstname;
    private String lastname;
    private Role role;

    public StandardUserDto(long participantId) {
        this.participantId = participantId;
    }
}
