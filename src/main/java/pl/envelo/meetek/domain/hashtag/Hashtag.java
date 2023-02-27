package pl.envelo.meetek.domain.hashtag;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    final public static String HASHTAG_PATTERN = "#[\\p{L}]{2,50}";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long hashtagId;
    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    @Pattern(regexp = HASHTAG_PATTERN,
            message = "Field must start with #, contain only letters and be between 2 and 50 characters")
    private String name;
    private boolean isActive;

    @Column(name = "usage_count")
    private int countOfHashtagUsage;

    public Hashtag(String name) {
        this.name = name;
    }

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
