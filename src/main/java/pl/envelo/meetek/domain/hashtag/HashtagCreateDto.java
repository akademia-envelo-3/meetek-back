package pl.envelo.meetek.domain.hashtag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HashtagCreateDto {

    private long hashtagId;
    private String name;

    public HashtagCreateDto(String name) {
        this.name = name;
    }
}
