package pl.envelo.meetek.model.request;

import pl.envelo.meetek.model.user.AppUser;

public abstract class Request {

    private Long requestId;
    private AppUser requester;
    private RequestStatus status;

}
