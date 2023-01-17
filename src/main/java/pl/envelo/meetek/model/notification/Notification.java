package pl.envelo.meetek.model.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.user.AppUser;

import java.time.LocalDateTime;
import java.util.Objects;
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
        return isDisplayed == that.isDisplayed && isImportant == that.isImportant && Objects.equals(notificationId, that.notificationId) && Objects.equals(category, that.category) && Objects.equals(notificationTypes, that.notificationTypes) && Objects.equals(recipient, that.recipient) && Objects.equals(addingDateTime, that.addingDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, isDisplayed, isImportant, category, notificationTypes, recipient, addingDateTime);
    }
}
