package pl.envelo.meetek.domain.group.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectionCreateDto {

    private String name;
    private String description;

}
