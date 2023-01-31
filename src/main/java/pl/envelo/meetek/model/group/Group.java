package pl.envelo.meetek.model.group;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.model.user.AppUser;
import pl.envelo.meetek.model.user.StandardUser;

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
    private String name;
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
