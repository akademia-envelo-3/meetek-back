package pl.envelo.meetek.model.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.user.AppUser;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long notificationId;
    private boolean isDisplayed;
    private boolean isImportant;
    @ManyToOne
    private NotificationCategory category;
    @OneToMany
    private Set<NotificationType> notificationTypes;
    @ManyToOne
    private AppUser recipient;
    private LocalDateTime addingDateTime;

}
