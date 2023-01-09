package pl.envelo.meetek.dto.request;

import pl.envelo.meetek.dto.comment.RequestCommentDto;
import pl.envelo.meetek.dto.user.StandardUserShortDto;

public class CategoryRequestDto {

    private long requestId;
    private StandardUserShortDto requester;
    private RequestStatusDto status;
    private String name;
    private RequestCommentDto comment;

}
