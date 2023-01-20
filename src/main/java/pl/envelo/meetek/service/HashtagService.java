package pl.envelo.meetek.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.Hashtag;
import pl.envelo.meetek.repository.HashtagRepo;

import java.util.List;

@AllArgsConstructor
@Service
public class HashtagService {

    private final HashtagRepo hashtagRepo;

    public List<Hashtag> getAllHashtags() {
        return hashtagRepo.findByOrderByNameAsc();
    }
}
