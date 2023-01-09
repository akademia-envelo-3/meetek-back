package pl.envelo.meetek.dto.comment;

import pl.envelo.meetek.dto.request.CategoryRequestDto;
import pl.envelo.meetek.dto.user.AdminDto;

import java.time.LocalDateTime;

public class RequestCommentDto {

    private long commentId;
    private AdminDto commentOwner;
    private LocalDateTime addingDateTime;
    private String comment;
    private CategoryRequestDto request;

}
