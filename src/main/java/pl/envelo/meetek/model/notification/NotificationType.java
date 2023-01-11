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

    DELETE, EDIT, EDIT_DATE_TIME,
    EDIT_LOCATION, CHANGE_OWNERSHIP, MENTIONED,
    QUOTED, ACCEPTED_REQUEST, DECLINED_REQUEST,
    RESPONSE_REQUIRED;

}


