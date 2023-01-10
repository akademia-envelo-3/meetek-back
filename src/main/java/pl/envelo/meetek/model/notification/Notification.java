package pl.envelo.meetek.model.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.user.AppUser;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class Notification {

    private Long notificationId;
    private boolean isDisplayed;
    private boolean isImportant;
    private NotificationCategory category;
    private Set<NotificationType> type;
    private AppUser recipient;
    private LocalDateTime addingDateTime;

}
