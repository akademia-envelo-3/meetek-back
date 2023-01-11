package pl.envelo.meetek.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Admin extends AppUser {

    public Admin(String password) {
        super(password, Role.ROLE_ADMIN);
    }

    @Override
    public String toString() {
        return super.toString() + " Admin{}";
    }
}
