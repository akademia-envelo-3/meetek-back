package pl.envelo.meetek.model.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.envelo.meetek.model.request.Request;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class RequestComment extends Comment {

    @OneToOne
    private Request request;

    @Override
    public String toString() {
        return super.toString() + " RequestComment{" +
                "request=" + request +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RequestComment that = (RequestComment) o;
        return Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), request);
    }
}
