package pl.envelo.meetek.domain.hashtag;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class HashtagService {

    private final HashtagRepo hashtagRepo;
    private final HashtagValidator hashtagValidator;

    @Transactional(readOnly = true)
    public Hashtag getHashtagById(long id) {
        return hashtagValidator.validateExists(id);
    }

    @Transactional
    public Hashtag saveHashtag(Hashtag hashtag) {
        hashtagValidator.validateNotDuplicate(hashtag.getName());
        hashtagValidator.validateInput(hashtag);
        hashtag.setActive(true);
        hashtag.setCountOfHashtagUsage(0);
        return hashtagRepo.save(hashtag);
    }

    @Transactional
    public Hashtag editHashtag(Hashtag hashtagToUpdate, Hashtag hashtagBody) {
        hashtagValidator.validateInput(hashtagBody);
        hashtagValidator.validateNotDuplicate(hashtagBody.getName());
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
