package pl.envelo.meetek.dto.notification;

import pl.envelo.meetek.dto.user.StandardUserShortDto;
import pl.envelo.meetek.model.notification.NotificationCategory;
import pl.envelo.meetek.model.notification.NotificationType;

import java.time.LocalDateTime;
import java.util.Set;

public class NotificationDto {

    private long notificationId;
    private boolean isDisplayed;
    private boolean isImportant;
    private NotificationCategory category;
    private Set<NotificationType> notificationTypes;
    private StandardUserShortDto recipient;
    private LocalDateTime addingDateTime;
    private long resourceId;

}