package pl.envelo.meetek.domain.hashtag;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.List;

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

}
