package pl.envelo.meetek.domain.hashtag;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pl.envelo.meetek.domain.hashtag.Hashtag.HASHTAG_PATTERN;

@AllArgsConstructor
@Service
public class HashtagService {

    private final HashtagRepo hashtagRepo;
    private final HashtagValidator hashtagValidator;
    private final DtoMapperService dtoMapperService;

    @Transactional(readOnly = true)
    public HashtagDto getHashtagById(long id) {
        return dtoMapperService.mapToHashtagDto(hashtagValidator.validateExists(id));
    }

    @Transactional
    public Hashtag createHashtag(HashtagCreateDto hashtagDto) {
        Hashtag hashtag = dtoMapperService.mapToHashtag(hashtagDto);
        hashtagValidator.validateNotDuplicate(hashtag.getName());
        hashtagValidator.validateInput(hashtag);
        hashtag.setActive(true);
        hashtag.setCountOfHashtagUsage(0);
        return hashtagRepo.save(hashtag);
    }

    @Transactional
    public Hashtag editHashtag(long id, HashtagCreateDto hashtagBody) {
        Hashtag hashtagToUpdate = hashtagValidator.validateExists(id);
        Hashtag hashtag = dtoMapperService.mapToHashtag(hashtagBody);
        hashtagValidator.validateInput(hashtag);
        hashtagValidator.validateNotDuplicate(hashtag.getName());
        hashtagToUpdate.setName(hashtag.getName());
        return hashtagRepo.save(hashtagToUpdate);
    }

    @Transactional(readOnly = true)
    public List<HashtagDto> getAllActiveHashtags() {
        return hashtagRepo.findAllByIsActiveTrueOrderByName()
                .stream()
                .map(dtoMapperService::mapToHashtagDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<HashtagDto> getAllHashtags() {
        return hashtagRepo.findAll(Sort.by("name"))
                .stream().map(dtoMapperService::mapToHashtagDto)
                .toList();
    }

    @Transactional
    public void changeCounterOfHashtag(long hashtagId, boolean counterIncrease) {
        Hashtag hashtag = hashtagValidator.validateExists(hashtagId);
        int counter = hashtag.getCountOfHashtagUsage();
        hashtag.setCountOfHashtagUsage(counterIncrease ? ++counter : --counter);
        hashtagRepo.save(hashtag);
    }

    @Transactional
    public Set<Hashtag> findAllHashtags(String toFindHashtags) {

        Set<Hashtag> foundHashtags = new HashSet<>();
        Pattern hashtagPattern = Pattern.compile(HASHTAG_PATTERN);
        Matcher hashtagMatcher = hashtagPattern.matcher(toFindHashtags);

        while (hashtagMatcher.find()) {
            String s = hashtagMatcher.group();
            Hashtag hashtag = new Hashtag();
            hashtag.setName(s);
            foundHashtags.add(hashtag);
        }
        return foundHashtags;
    }

    @Transactional
    public Optional<Hashtag> findHashtagByName(String name) {
        return hashtagRepo.findByName(name);
    }

    @Transactional
    public Set<Hashtag> checkHashtagSet(Set<Hashtag> usedHashtags, Set<Hashtag> hashtagsToCheck) {
        Set<Hashtag> checkedHashtags = new HashSet<>();
        Hashtag newHashtag;
        if (usedHashtags == null) {usedHashtags = new HashSet<>();}
        if (hashtagsToCheck == null) {return null;}

        for (Hashtag hashtag : hashtagsToCheck) {
            if (!containsName(checkedHashtags, hashtag.getName()) && !containsName(usedHashtags, hashtag.getName())) {
                if (hashtag.getHashtagId() != null && hashtag.getHashtagId() != 0) {
                    newHashtag = hashtagValidator.validateExists(hashtag.getHashtagId());
                    hashtagValidator.validateHashtagIsActive(newHashtag.getHashtagId());
                } else if (findHashtagByName(hashtag.getName()).isPresent()) {
                    newHashtag = findHashtagByName(hashtag.getName()).get();
                    hashtagValidator.validateHashtagIsActive(newHashtag.getHashtagId());
                } else {
                    newHashtag = createHashtag(new HashtagCreateDto(hashtag.getName()));
                }
                changeCounterOfHashtag(newHashtag.getHashtagId(), true);
                checkedHashtags.add(newHashtag);
            }
        }
        return checkedHashtags;
    }

    private boolean containsName(Set<Hashtag> hashtags, String name) {
        return hashtags.stream().anyMatch(hashtag -> name.equals(hashtag.getName()));
    }

}
