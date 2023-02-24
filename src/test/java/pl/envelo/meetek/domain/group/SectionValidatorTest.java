package pl.envelo.meetek.domain.group;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.group.model.Section;
import pl.envelo.meetek.domain.user.StandardUserRepo;
import pl.envelo.meetek.domain.user.StandardUserValidator;
import pl.envelo.meetek.exceptions.DuplicateException;
import pl.envelo.meetek.exceptions.NotFoundException;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SectionValidatorTest {

    @Mock
    private SectionRepo sectionRepo;

    @Mock
    private StandardUserRepo userRepo;

    @InjectMocks
    private SectionValidator sectionValidator;

    @InjectMocks
    private StandardUserValidator userValidator;

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
    void testThrowNotFoundException_SectionDoesNotExists() {
        when(sectionRepo.findById(1L)).thenReturn(Optional.empty());
        SectionValidator validator = new SectionValidator(null, sectionRepo, userRepo, userValidator);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> validator.validateExists(1L));
        assertEquals("Section with id 1 not found", exception.getMessage());
    }

    @Test
    public void shouldThrowDuplicateExceptionWhenActiveSectionNameAlreadyExists() {
        // Given
        Section section = new Section();
        String name = "Test Section";
        section.setIsActive(true);
        when(sectionRepo.findByName(name)).thenReturn(Optional.of(section));

        // When/Then
        assertThrows(DuplicateException.class, () -> sectionValidator.validateNotActiveDuplicate(name));
    }

    @Test
    public void shouldReturnSectionWhenInactiveSectionNameAlreadyExists() {
        // Given
        Section section = new Section();
        String name = "Test Section";
        section.setIsActive(false);
        when(sectionRepo.findByName(name)).thenReturn(Optional.of(section));

        // When
        Section result = sectionValidator.validateNotActiveDuplicate(name);

        // Then
        assertEquals(section, result);
    }

    @Test
    public void shouldReturnNullWhenSectionNameDoesNotExistForInactiveSections() {
        // Given
        String name = "Test Section";
        when(sectionRepo.findByName(name)).thenReturn(Optional.empty());

        // When
        Section result = sectionValidator.validateNotActiveDuplicate(name);

        // Then
        assertEquals(null, result);
    }
}



