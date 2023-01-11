package pl.envelo.meetek.model.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class RequestBox {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long requestBoxId;
    @OneToMany
    private Set<Request> requests;

}
