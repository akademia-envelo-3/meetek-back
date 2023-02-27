package pl.envelo.meetek.domain.hashtag;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.category.Category;
import pl.envelo.meetek.domain.category.CategoryValidator;
import pl.envelo.meetek.exceptions.DuplicateException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HashtagValidatorTest {

    @Mock
    private HashtagRepo hashtagRepo;

    @InjectMocks
    private HashtagValidator hashtagValidator;

    //Exists
    @Test
    void testValidateIfHashtagExists() {
        Hashtag hashtag = new Hashtag();
        hashtag.setHashtagId(1L);

        when(hashtagRepo.findById(1L)).thenReturn(Optional.of(hashtag));
        HashtagValidator validator = new HashtagValidator(null, hashtagRepo);

        Hashtag validatedHashtag = validator.validateExists(1L);

        assertEquals(hashtag, validatedHashtag);
    }

    @Test
    void testNotThrowException_WhenHashtagIsNotDuplicated() {

        when(hashtagRepo.findByName("#Hashtag")).thenReturn(Optional.empty());
        HashtagValidator validator = new HashtagValidator(null, hashtagRepo);

        assertDoesNotThrow(() -> validator.validateNotDuplicate("#Hashtag"));
    }

    @Test
    void testThrowDuplicateException_WhenHashtagNameDuplicateAndIsActive() {
        Hashtag hashtag = new Hashtag();
        hashtag.setName("#HASHTAG");
        hashtag.setActive(true);

        when(hashtagRepo.findByName("#HASHTAG")).thenReturn(Optional.of(hashtag));
        HashtagValidator validator = new HashtagValidator(null, hashtagRepo);

        DuplicateException exception = assertThrows(
                DuplicateException.class,
                () -> validator.validateNotDuplicate("#HASHTAG"));
        assertEquals("Hashtag with name #HASHTAG already exists", exception.getMessage());
    }

}