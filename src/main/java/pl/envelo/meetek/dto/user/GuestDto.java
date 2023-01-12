package pl.envelo.meetek.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto {

    private long participantId;
    private String firstname;
    private String lastname;
    private String mail;

}
