package pl.envelo.meetek.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.dto.request.CategoryRequestDto;
import pl.envelo.meetek.dto.user.AdminDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCommentDto {

    private long commentId;
    private AdminDto commentOwner;
    private LocalDateTime addingDateTime;
    private String comment;
    private CategoryRequestDto request;

}
