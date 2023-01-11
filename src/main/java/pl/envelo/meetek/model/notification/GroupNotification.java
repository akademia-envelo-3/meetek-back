package pl.envelo.meetek.model.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.group.Group;

@AllArgsConstructor
@Getter
@Entity
public class GroupNotification extends Notification {

    @ManyToOne
    private Group group;

}
