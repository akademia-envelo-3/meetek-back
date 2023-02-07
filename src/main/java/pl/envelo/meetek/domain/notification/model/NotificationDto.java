package pl.envelo.meetek.domain.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.user.model.StandardUserShortDto;

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