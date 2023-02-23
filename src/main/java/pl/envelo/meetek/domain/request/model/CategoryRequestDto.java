package pl.envelo.meetek.domain.request.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.category.CategoryDto;
import pl.envelo.meetek.domain.comment.model.RequestCommentDto;
import pl.envelo.meetek.domain.user.model.StandardUserDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {

    private long requestId;
    private StandardUserDto requester;
    private String requestStatus;
    private String name;
    private CategoryDto category;
    private RequestCommentDto comment;

    public CategoryRequestDto(String name) {
        this.name = name;
    }
}
