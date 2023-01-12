package pl.envelo.meetek.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HashtagDto {

    private long hashtagId;
    private String name;
    private boolean isActive;
    private int countOfHashtagUsage;

}
