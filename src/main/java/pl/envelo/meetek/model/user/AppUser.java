package pl.envelo.meetek.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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
        return super.toString() + "AppUser{" +
                "password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(password, appUser.password) && role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password, role);
    }

}
