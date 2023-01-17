package pl.envelo.meetek.model.user;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Admin extends AppUser {

    public Admin(String password) {
        super(password, Role.ROLE_ADMIN);
    }

    @Override
    public String toString() {
        return super.toString() + " Admin{}";
    }
}
