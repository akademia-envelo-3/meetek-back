package pl.envelo.meetek.domain.hashtag;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.exceptions.ArgumentNotValidException;
import pl.envelo.meetek.exceptions.DuplicateException;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

@Service
public class HashtagValidator extends ValidatorService<Hashtag> {

    private final HashtagRepo repository;

    public HashtagValidator(Validator validator, HashtagRepo repository) {
        super(validator);
        this.repository = repository;
    }


    @Override
    public Hashtag validateExists(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Hashtag with id " + id + " not found"));
    }

    public void validateNotDuplicate(String name) {
        repository.findByName(name).ifPresent(hashtag -> {
            if (hashtag.isActive()) {
                throw new DuplicateException("Hashtag with name " + name + " already exists");
            } else {
                throw new DuplicateException("Hashtag with name " + name + " already exists but is not active");
            }
        });
    }

    public Hashtag validateHashtagIsActive(long hashtagId){
        Hashtag hashtag = validateExists(hashtagId);
        if(!hashtag.isActive()){
            throw new ArgumentNotValidException("Hashtag with id " + hashtagId + ": " + hashtag.getName() + " is not active");
        }
        return hashtag;
    }

}
