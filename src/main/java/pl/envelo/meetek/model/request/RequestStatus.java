package pl.envelo.meetek.model.request;

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
@Table(name = "request_statuses")
public class RequestStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long statusId;
    private String status;

    @Override
    public String toString() {
        return "RequestStatus{" +
                "statusId=" + statusId +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestStatus that = (RequestStatus) o;
        return Objects.equals(statusId, that.statusId) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, status);
    }
}
