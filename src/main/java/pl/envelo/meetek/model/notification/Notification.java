package pl.envelo.meetek.model.notification;

import pl.envelo.meetek.model.user.AppUser;

import java.time.LocalDateTime;
import java.util.Set;

public abstract class Notification {

    private Long notificationId;
    private boolean isDisplayed;
    private boolean isImportant;
    private NotificationCategory category;
    private Set<NotificationType> type;
    private AppUser recipient;
    private LocalDateTime addingDateTime;
}
