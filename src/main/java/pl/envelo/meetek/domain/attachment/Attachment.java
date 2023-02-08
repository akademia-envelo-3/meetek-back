package pl.envelo.meetek.domain.attachment;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long attachmentId;
    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    @URL(message = "Field must be URL")
    private String link;

    @Override
    public String toString() {
        return "Attachment{" +
                "attachmentId=" + attachmentId +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(attachmentId, that.attachmentId) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attachmentId, link);
    }
}
