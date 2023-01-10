package pl.envelo.meetek.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.comment.RequestComment;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryRequest extends Request {

    private String name;
    private RequestComment comment;

}
