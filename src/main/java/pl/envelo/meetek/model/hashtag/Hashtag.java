package pl.envelo.meetek.model.hashtag;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long hashtagId;
    private String name;
    private boolean isActive;
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
