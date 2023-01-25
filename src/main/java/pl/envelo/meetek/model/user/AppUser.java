package pl.envelo.meetek.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AppUser extends Participant {

    private String password;
    private Role role;

    public AppUser(Long participantId, String firstname, String lastname, String mail, String password, Role role) {
        super(participantId, firstname, lastname, mail);
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return super.toString() + " AppUser{" +
                "password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
