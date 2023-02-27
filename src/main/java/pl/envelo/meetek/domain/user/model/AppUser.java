package pl.envelo.meetek.domain.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public AppUser(Long participantId, String firstname) {
        super(participantId, firstname);
    }

    public AppUser(Long participantId) {
        super(participantId);
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
