package pl.envelo.meetek.domain.attachment;

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
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long attachmentId;
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
