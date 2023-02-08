package pl.envelo.meetek.domain.group.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Groups")
public abstract class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long groupId;
    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    @Size(min = 2, max = 50, message = "Field must be between {min} and {max} characters")
    private String name;
    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    @Size(min = 20, max = 2000, message = "Field must be between {min} and {max} characters")
    private String description;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private StandardUser groupOwner;

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", groupOwner=" + groupOwner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return isActive == group.isActive && groupId.equals(group.groupId) && name.equals(group.name) && description.equals(group.description) && groupOwner.equals(group.groupOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, name, description, isActive, groupOwner);
    }
}
