package pl.envelo.meetek.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "admins")
public class Admin extends AppUser {

    public Admin(String password) {
        super(password, Role.ROLE_ADMIN);
    }

    @Override
    public String toString() {
        return super.toString() + " Admin{}";
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
