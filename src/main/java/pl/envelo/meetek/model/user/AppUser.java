package pl.envelo.meetek.model.user;

import jakarta.persistence.Entity;
import lombok.Getter;


@Getter
@Entity
public abstract class AppUser extends Participant {

    private String password;
    private Role role;

}
