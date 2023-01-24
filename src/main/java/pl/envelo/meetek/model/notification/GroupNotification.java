package pl.envelo.meetek.model.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.model.group.Group;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notifications_group")
public class GroupNotification extends Notification {

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Override
    public String toString() {
        return super.toString() + " GroupNotification{" +
                "group=" + group +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupNotification that = (GroupNotification) o;
        return Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group);
    }
}
