package pl.envelo.meetek.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private long categoryId;
    private String name;
    private boolean isActive;

    public CategoryDto(long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }


}
