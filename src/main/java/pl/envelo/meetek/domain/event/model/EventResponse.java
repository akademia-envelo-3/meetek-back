package pl.envelo.meetek.domain.event.model;

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
@Table(name = "event_responses")
public class EventResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "response_id")
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
