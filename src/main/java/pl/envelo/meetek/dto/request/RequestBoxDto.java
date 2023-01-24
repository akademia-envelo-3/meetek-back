package pl.envelo.meetek.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBoxDto {

    private long requestBoxId;
    private List<CategoryRequestDto> requests;

}
