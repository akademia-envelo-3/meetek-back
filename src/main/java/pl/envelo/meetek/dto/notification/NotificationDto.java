package pl.envelo.meetek.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.dto.user.StandardUserShortDto;
import pl.envelo.meetek.model.notification.NotificationCategory;
import pl.envelo.meetek.model.notification.NotificationType;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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