package pl.envelo.meetek.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestBox {

    private Long requestBoxId;
    private Set<Request> requests;

}
