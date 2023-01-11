package pl.envelo.meetek.model.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class EventResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long eventResponseId;
    private String response;

    @Override
    public String toString() {
        return "EventResponse{" +
                "eventResponseId=" + eventResponseId +
                ", response='" + response + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventResponse that = (EventResponse) o;
        return Objects.equals(eventResponseId, that.eventResponseId) && Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventResponseId, response);
    }
}
