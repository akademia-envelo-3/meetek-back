package pl.envelo.meetek.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.model.user.Role;

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

}
