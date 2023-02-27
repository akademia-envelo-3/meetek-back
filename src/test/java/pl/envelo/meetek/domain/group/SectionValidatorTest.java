package pl.envelo.meetek.domain.group;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.group.model.Section;
import pl.envelo.meetek.domain.user.StandardUserRepo;
import pl.envelo.meetek.domain.user.StandardUserValidator;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.exceptions.DuplicateException;
import pl.envelo.meetek.exceptions.NotAuthorizedUserException;
import pl.envelo.meetek.exceptions.NotFoundException;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SectionValidatorTest {

    @Mock
    private SectionRepo sectionRepo;

    @Mock
    private StandardUserRepo userRepo;

    @Mock
    private SectionService sectionService;

    @Mock
    private SectionController sectionController;
    @Mock
    private StandardUserValidator userValidator;
    @InjectMocks
    private SectionValidator sectionValidator;



    //Exists
    @Test
    void testValidateIfSectionExists() {
        Section section = new Section();
        section.setGroupId(1L);
        when(sectionRepo.findById(1L)).thenReturn(Optional.of(section));
        SectionValidator validator = new SectionValidator(null, sectionRepo, userRepo, userValidator);

        Section validatedSection = validator.validateExists(1L);

        assertEquals(section, validatedSection);
    }


    //Doesnt Exist
    @Test
    void testThrowNotFoundException_WhenSectionDoesNotExists() {
        when(sectionRepo.findById(1L)).thenReturn(Optional.empty());
        SectionValidator validator = new SectionValidator(null, sectionRepo, userRepo, userValidator);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> validator.validateExists(1L));
        assertEquals("Section with id 1 not found", exception.getMessage());
    }

    @Test
    public void shouldThrowDuplicateException_WhenActiveSectionNameAlreadyExists() {
        // Given
        Section section = new Section();
        String name = "Test Section";
        section.setName(name);
        section.setIsActive(true);
        when(sectionRepo.findByName(name)).thenReturn(Optional.of(section));

        assertThrows(DuplicateException.class, () -> sectionValidator.validateNotActiveDuplicate(name));
    }

    @Test
    public void testValidateSection_WhenSectionNameDoesNotExistForInactiveSections() {

        String name = "Test Section";
        when(sectionRepo.findByName(name)).thenReturn(Optional.empty());


        Section result = sectionValidator.validateNotActiveDuplicate(name);

        assertEquals(null, result);
    }
    @Test
    public void shouldThrowDuplicateExceptionWhenSectionNameAlreadyExists() {
        String sectionName = "Section Name";
        Section section = new Section();
        section.setGroupId(1L);
        section.setName(sectionName);
        section.setIsActive(true);

        when(sectionRepo.findByName(sectionName)).thenReturn(Optional.of(section));

        DuplicateException ex = org.junit.jupiter.api.Assertions.assertThrows(
                DuplicateException.class,
                () -> sectionValidator.validateNotActiveDuplicate(sectionName)
        );
        assertEquals("Section with name " + sectionName + " already exists", ex.getMessage());
    }

    @Test
    void testValidateOwner() {
        Section section = new Section();
        section.setGroupOwner(new StandardUser(1L, "user1"));

        StandardUser newOwner = new StandardUser(2L, "user2");

        when(userValidator.validateExists(newOwner.getParticipantId())).thenReturn(newOwner);
        StandardUser result = sectionValidator.validateOwner(section, newOwner);
        assertEquals(newOwner, result);

        result = sectionValidator.validateOwner(section, section.getGroupOwner());
        assertEquals(section.getGroupOwner(), result);

        result = sectionValidator.validateOwner(section, null);
        assertEquals(section.getGroupOwner(), result);

        when(userValidator.validateExists(2L)).thenThrow(new NotFoundException("User with id 2 not found"));
        assertThrows(NotFoundException.class, () -> sectionValidator.validateOwner(section, new StandardUser(2L, "user2")));
    }

    @Test
    void validateOwnerForAdmin_WhenNewOwnerIdIsCurrentOwnerAndThrowsDuplicateException() {
        Section section = new Section();
        StandardUser currentOwner = new StandardUser();
        section.setGroupOwner(currentOwner);
        currentOwner.setParticipantId(1L);
        section.getGroupOwner().setParticipantId(currentOwner.getParticipantId());

        long newOwnerId = currentOwner.getParticipantId();

        assertThrows(DuplicateException.class, () -> {
            sectionValidator.validateOwnerForAdmin(section, newOwnerId);
        });
    }

    @Test
    void validateOwnerForAdmin_WhenNewOwnerIdIsValid_ReturnsNewOwner() {
        Section section = new Section();
        StandardUser currentOwner = new StandardUser();
        currentOwner.setParticipantId(1L);
        section.setGroupOwner(currentOwner);
        section.getGroupOwner().setParticipantId(currentOwner.getParticipantId());

        long newOwnerId = 2L;
        StandardUser newOwner = new StandardUser();
        newOwner.setParticipantId(newOwnerId);

        when(userValidator.validateExists(newOwnerId)).thenReturn(newOwner);

        StandardUser result = sectionValidator.validateOwnerForAdmin(section, newOwnerId);

        verify(userValidator).validateExists(newOwnerId);
        assert(result.getParticipantId() == newOwnerId);
    }

    @Test
    public void testValidateSectionOwner_WhenUserIsNotTheSectionOwner_ThrowNotAuthorizedUserException() {
        long ownerId = 1L;
        long participantId = 2L;

        StandardUser owner = new StandardUser();
        owner.setParticipantId(ownerId);

        Section section = new Section();
        section.setGroupOwner(owner);

        StandardUser user = new StandardUser();
        user.setParticipantId(participantId);

        NotAuthorizedUserException ex = org.junit.jupiter.api.Assertions.assertThrows(
                NotAuthorizedUserException.class,
                () -> sectionValidator.validateUserAuthorized(section, user)
        );
       assertEquals("Only section owner can modify section", ex.getMessage());
    }
    @Test
    void testValidateNotActiveDuplicate_whenSectionWithNameExistsAndIsActive_ThrowDuplicateException() {

        String name = "Section 1";
        Section section = new Section();
        section.setGroupId(1L);
        section.setName("Section 2");
        section.setIsActive(true);
        Optional<Section> sectionFromDto = Optional.of(section);
        when(sectionRepo.findByName(name)).thenReturn(sectionFromDto);

        assertThrows(DuplicateException.class, () -> sectionValidator.validateNotActiveDuplicate(section, name));

        verify(sectionRepo).findByName(name);
    }
}



