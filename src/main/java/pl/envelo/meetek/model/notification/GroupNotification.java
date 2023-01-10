package pl.envelo.meetek.model.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.group.Group;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GroupNotification extends Notification {

    private Group group;

}
