package pl.envelo.meetek.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.dto.comment.RequestCommentDto;
import pl.envelo.meetek.dto.user.StandardUserShortDto;
import pl.envelo.meetek.model.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {

    private long requestId;
    private StandardUserShortDto requester;
    private RequestStatusDto status;
    private String name;
    private Category category;
    private RequestCommentDto comment;

}
