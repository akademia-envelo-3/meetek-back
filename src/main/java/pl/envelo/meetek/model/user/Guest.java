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

    @Override
    public String toString() {
        return super.toString() + " Guest{}";
    }

}
