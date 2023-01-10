package pl.envelo.meetek.model.user;

import jakarta.persistence.Entity;
import lombok.Getter;


@Getter
@Entity
public abstract class AppUser extends Participant {

    private String password;
    private Role role;

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
