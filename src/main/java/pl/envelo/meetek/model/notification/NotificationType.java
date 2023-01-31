package pl.envelo.meetek.model.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notification_types")
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long typeId;
    private String type;

    @Override
    public String toString() {
        return "NotificationType{" +
                "notificationTypeId=" + typeId +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationType that = (NotificationType) o;
        return Objects.equals(typeId, that.typeId) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, type);
    }
}


