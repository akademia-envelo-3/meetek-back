package pl.envelo.meetek.model.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long notificationTypeId;
    private String type;

}


