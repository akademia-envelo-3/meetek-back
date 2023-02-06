package pl.envelo.meetek.dto.comment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.dto.attachment.AttachmentDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventCommentCreateDto {

    private String comment;
    private List<AttachmentDto> attachments;

}
