package pl.envelo.meetek.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.user.model.Admin;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StandardUserValidatorTest {

    @Mock
    private StandardUserRepo standardUserRepo;

    @InjectMocks
    private StandardUserValidator standardUserValidator;

    @Test
    void testValidateIfUserExists() {
        StandardUser standardUser = new StandardUser();
        standardUser.setParticipantId(1L);

        when(standardUserRepo.findById(1L)).thenReturn(Optional.of(standardUser));
        StandardUserValidator validator = new StandardUserValidator(null, standardUserRepo);

        StandardUser validatedStandardUser = validator.validateExists(1L);

        assertEquals(standardUser, validatedStandardUser);
    }
}