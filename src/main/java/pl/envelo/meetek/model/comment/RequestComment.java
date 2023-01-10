package pl.envelo.meetek.model.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.request.Request;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestComment extends Comment {

    private Request request;
}
