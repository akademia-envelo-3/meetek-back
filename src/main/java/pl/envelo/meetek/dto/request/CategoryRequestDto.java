package pl.envelo.meetek.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.dto.category.CategoryDto;
import pl.envelo.meetek.dto.comment.RequestCommentDto;
import pl.envelo.meetek.dto.user.StandardUserShortDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {

    private long requestId;
    private StandardUserShortDto requester;
    private String requestStatus;
    private String name;
    private CategoryDto category;
    private RequestCommentDto comment;

}
