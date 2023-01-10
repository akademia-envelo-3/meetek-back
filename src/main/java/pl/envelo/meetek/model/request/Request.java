package pl.envelo.meetek.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.user.AppUser;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class Request {

    private Long requestId;
    private AppUser requester;
    private RequestStatus status;

}
