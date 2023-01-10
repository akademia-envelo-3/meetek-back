package pl.envelo.meetek.model.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.request.Request;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class RequestComment extends Comment {

    @OneToOne
    private Request request;

}
