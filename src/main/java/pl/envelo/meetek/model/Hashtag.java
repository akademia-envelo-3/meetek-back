package pl.envelo.meetek.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Hashtag {

    private Long hashtagId;
    private String name;
    private boolean isActive;
    private int countOfHashtagUsage;

}
