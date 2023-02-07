package pl.envelo.meetek.domain.notification.model;

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
@Table(name = "notification_categories")
public class NotificationCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long categoryId;
    private String category;

    @Override
    public String toString() {
        return "NotificationCategory{" +
                "categoryId=" + categoryId +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationCategory that = (NotificationCategory) o;
        return Objects.equals(categoryId, that.categoryId) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, category);
    }
}
