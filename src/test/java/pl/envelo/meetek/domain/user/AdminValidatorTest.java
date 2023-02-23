package pl.envelo.meetek.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.user.model.Admin;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminValidatorTest {

    @Mock
    private AdminRepo adminRepo;

    @InjectMocks
    private AdminValidator adminValidator;

    @Test
    void testValidateIfAdminExists() {
        Admin admin = new Admin();
        admin.setParticipantId(1L);
        when(adminRepo.findById(1L)).thenReturn(Optional.of(admin));
        AdminValidator validator = new AdminValidator(null, adminRepo);

        Admin validatedAdmin = validator.validateExists(1L);

        assertEquals(admin, validatedAdmin);
    }

}