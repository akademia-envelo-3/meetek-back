package pl.envelo.meetek.model.request;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.comment.RequestComment;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class CategoryRequest extends Request {

    private String name;
    @OneToOne
    private RequestComment comment;

}
