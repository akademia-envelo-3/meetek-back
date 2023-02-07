package pl.envelo.meetek.domain.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private StandardUser requester;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", requester=" + requester +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(requestId, request.requestId) && Objects.equals(requester, request.requester) && Objects.equals(status, request.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, requester, status);
    }
}
