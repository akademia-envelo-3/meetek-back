package pl.envelo.meetek.model.user;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Guest extends Participant {

    @Override
    public String toString() {
        return super.toString() + "Guest{}";
    }

}
