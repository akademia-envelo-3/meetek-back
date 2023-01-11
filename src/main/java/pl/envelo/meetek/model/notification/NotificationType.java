package pl.envelo.meetek.model.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long notificationTypeId;
    private String type;

    @Override
    public String toString() {
        return "NotificationType{" +
                "notificationTypeId=" + notificationTypeId +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationType that = (NotificationType) o;
        return Objects.equals(notificationTypeId, that.notificationTypeId) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationTypeId, type);
    }
}


