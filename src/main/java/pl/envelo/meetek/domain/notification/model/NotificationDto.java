package pl.envelo.meetek.domain.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.user.model.StandardUserDto;

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
    private StandardUserDto recipient;
    private LocalDateTime addingDateTime;
    private long resourceId;

    public NotificationDto(long notificationId) {
        this.notificationId = notificationId;
    }
}