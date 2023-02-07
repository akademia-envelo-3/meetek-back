package pl.envelo.meetek.service.hashtag;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.hashtag.Hashtag;
import pl.envelo.meetek.repository.hashtag.HashtagRepo;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HashtagService {

    private final HashtagRepo hashtagRepo;

    @Transactional(readOnly = true)
    public Optional<Hashtag> getHashtagById(long id) {
        return hashtagRepo.findById(id);
    }

    @Transactional
    public Hashtag saveHashtag(Hashtag hashtag) {
        hashtag.setActive(true);
        hashtag.setCountOfHashtagUsage(0);
        return hashtagRepo.save(hashtag);
    }

    @Transactional
    public Hashtag editHashtag(Hashtag hashtagToUpdate, Hashtag hashtagBody) {
        hashtagToUpdate.setName(hashtagBody.getName());
        return hashtagRepo.save(hashtagToUpdate);
    }

    @Transactional(readOnly = true)
    public List<Hashtag> getAllActiveHashtags() {
        return hashtagRepo.findAllByIsActiveTrueOrderByName();
    }

    @Transactional(readOnly = true)
    public List<Hashtag> getAllHashtags() {
        return hashtagRepo.findAll(Sort.by("name"));
    }

}
