package pl.envelo.meetek.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class Participant {

    @Id
    private Long participantId;
    private String firstname;
    private String lastname;
    private String mail;

}
