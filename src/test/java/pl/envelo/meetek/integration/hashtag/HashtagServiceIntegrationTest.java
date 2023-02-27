package pl.envelo.meetek.integration.hashtag;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.hashtag.Hashtag;
import pl.envelo.meetek.domain.hashtag.HashtagDto;
import pl.envelo.meetek.domain.hashtag.HashtagRepo;
import pl.envelo.meetek.domain.hashtag.HashtagService;
import pl.envelo.meetek.exceptions.DuplicateException;
import pl.envelo.meetek.exceptions.NotFoundException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class HashtagServiceIntegrationTest {

    @Autowired
    HashtagService hashtagService;

    @Autowired
    HashtagRepo hashtagRepo;

    @Test
    void getHashtagById_whenExist_Integration(){
        // given
        Hashtag hashtag = hashtagRepo.save(new Hashtag(0L,"#Active1",true,1 ));
        // when
        HashtagDto hashtagDto = hashtagService.getHashtagById(hashtag.getHashtagId());
        // then
        assertThat(hashtagDto.getName()).isEqualTo(hashtag.getName());
    }

    @Test
    void getHashtagById_whenNotFoundIntegration(){
        // given
        // when
        // then
        assertThrows(NotFoundException.class, () -> hashtagService.getHashtagById(1L));
    }

    @Test
    void getAllActiveHashtags_whenExist_Integration(){
        // given
        Hashtag hashtagActive1 = hashtagRepo.save(new Hashtag(0L,"#Active1",true,1 ));
        Hashtag hashtagActive2 = hashtagRepo.save(new Hashtag(0L,"#Active2",true,1 ));
        Hashtag hashtagNotActive = hashtagRepo.save(new Hashtag(0L,"#NotActive",false,1 ));
        // when
        List<HashtagDto> hashtagDtos = hashtagService.getAllActiveHashtags();
        // then
        assertThat(hashtagDtos.get(0).getName()).isEqualTo(hashtagActive1.getName());
        assertThat(hashtagDtos.get(1).getName()).isEqualTo(hashtagActive2.getName());
        assertThat(hashtagDtos.size()).isEqualTo(2);
    }

    @Test
    void getAllHashtags_whenExist_Integration(){
        // given
        Hashtag hashtagActive1 = hashtagRepo.save(new Hashtag(0L,"#Active1",true,1 ));
        Hashtag hashtagActive2 = hashtagRepo.save(new Hashtag(0L,"#Active2",true,1 ));
        Hashtag hashtagNotActive = hashtagRepo.save(new Hashtag(0L,"#NotActive",false,1 ));
        // when
        List<HashtagDto> hashtagDtos = hashtagService.getAllHashtags();
        // then
        assertThat(hashtagDtos.get(0).getName()).isEqualTo(hashtagActive1.getName());
        assertThat(hashtagDtos.get(1).getName()).isEqualTo(hashtagActive2.getName());
        assertThat(hashtagDtos.get(2).getName()).isEqualTo(hashtagNotActive.getName());
        assertThat(hashtagDtos.size()).isEqualTo(3);
    }

}
