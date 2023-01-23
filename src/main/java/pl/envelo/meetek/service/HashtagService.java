package pl.envelo.meetek.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.Hashtag;
import pl.envelo.meetek.repository.HashtagRepo;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HashtagService {

    private final HashtagRepo hashtagRepo;

    public Optional<Hashtag> getHashtagById(long id) {
        return hashtagRepo.findById(id);
    }

    public Hashtag saveNewHashtag(Hashtag hashtag) {
        return hashtagRepo.save(hashtag);
    }

    public Hashtag editHashtag(long hashtagId, Hashtag hashtag) {
        hashtag.setHashtagId(hashtagId);
        return hashtagRepo.save(hashtag);
    }

    public List<Hashtag> getAllActiveHashtags() {
        return hashtagRepo.findAllByIsActiveOrderByName(true);
    }

    public List<Hashtag> getAllHashtags() {
        return hashtagRepo.findByOrderByNameAsc();
    }
}
