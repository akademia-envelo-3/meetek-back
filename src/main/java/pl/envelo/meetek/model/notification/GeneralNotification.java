package pl.envelo.meetek.model.notification;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Entity
public class GeneralNotification extends Notification {

    @Override
    public String toString() {
        return super.toString() + " GeneralNotification{}";
    }

}
