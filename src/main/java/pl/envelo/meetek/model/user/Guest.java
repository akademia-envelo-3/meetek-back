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
@Table(name = "guests")
public class Guest extends Participant {

    public Guest(Long participantId, String firstname, String lastname, String mail) {
        super(participantId, firstname, lastname, mail);
    }

    @Override
    public String toString() {
        return super.toString() + " Guest{}";
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
