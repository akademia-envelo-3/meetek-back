package pl.envelo.meetek.domain.notification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.user.model.AppUser;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long notificationId;
    private boolean isDisplayed;
    private boolean isImportant;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private NotificationCategory category;

    @ManyToMany
    @JoinTable(name = "notifications_x_types",
            joinColumns = @JoinColumn(name = "notification_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<NotificationType> notificationTypes;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private AppUser recipient;
    private LocalDateTime addingDateTime;

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", isDisplayed=" + isDisplayed +
                ", isImportant=" + isImportant +
                ", category=" + category +
                ", notificationTypes=" + notificationTypes +
                ", recipient=" + recipient +
                ", addingDateTime=" + addingDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return isDisplayed == that.isDisplayed && isImportant == that.isImportant && Objects.equals(notificationId, that.notificationId) && Objects.equals(category, that.category) && Objects.equals(addingDateTime, that.addingDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, isDisplayed, isImportant, category, addingDateTime);
    }
}
