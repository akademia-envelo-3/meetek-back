package pl.envelo.meetek.model.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.user.AppUser;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long requestId;
    @ManyToOne
    private AppUser requester;
    @ManyToOne
    private RequestStatus status;

}
