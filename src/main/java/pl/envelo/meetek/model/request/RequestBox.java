package pl.envelo.meetek.model.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "request_boxes")
public class RequestBox {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long requestBoxId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "request_boxes_x_requests",
            joinColumns = @JoinColumn(name = "request_box_id"),
            inverseJoinColumns = @JoinColumn(name = "request_id"))
    private List<CategoryRequest> requests;

    @Override
    public String toString() {
        return "RequestBox{" +
                "requestBoxId=" + requestBoxId +
                ", requests=" + requests +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestBox that = (RequestBox) o;
        return Objects.equals(requestBoxId, that.requestBoxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestBoxId);
    }

}
