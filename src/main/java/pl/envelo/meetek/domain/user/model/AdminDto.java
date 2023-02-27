package pl.envelo.meetek.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    private long participantId;
    private String firstname;
    private String lastname;
    private String mail;
    private Role role;

    public AdminDto(long participantId) {
        this.participantId = participantId;
    }
}
