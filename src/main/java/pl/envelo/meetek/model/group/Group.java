package pl.envelo.meetek.model.group;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class Group {

    private Long groupId;
    private String name;
    private String description;
    private boolean isActive;

}
