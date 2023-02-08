package pl.envelo.meetek.domain.hashtag;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "hashtags")
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long hashtagId;
    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    @Size(min = 2, max = 50, message = "Field must be between {min} and {max} characters")
    private String name;
    private boolean isActive;

    @Column(name = "usage_count")
    private int countOfHashtagUsage;

    @Override
    public String toString() {
        return "Hashtag{" +
                "hashtagId=" + hashtagId +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", countOfHashtagUsage=" + countOfHashtagUsage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return isActive == hashtag.isActive && countOfHashtagUsage == hashtag.countOfHashtagUsage && Objects.equals(hashtagId, hashtag.hashtagId) && Objects.equals(name, hashtag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashtagId, name, isActive, countOfHashtagUsage);
    }
}
