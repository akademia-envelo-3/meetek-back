package pl.envelo.meetek.domain.group;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.envelo.meetek.domain.category.CategoryRepo;
import pl.envelo.meetek.domain.category.CategoryService;
import pl.envelo.meetek.domain.group.model.Section;
import pl.envelo.meetek.domain.group.model.SectionLongDto;
import pl.envelo.meetek.domain.group.model.SectionShortDto;
import pl.envelo.meetek.domain.user.StandardUserValidator;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SectionServiceTest {

    @Mock
    private SectionRepo sectionRepo;

    @Mock
    private SectionController sectionController;

    @Mock
    private StandardUserValidator standardUserValidator;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    Section section;
    @Mock
    private SectionValidator sectionValidator;

    @Mock
    private CategoryService categoryService;
    @Mock
    private DtoMapperService mapperService;

    @InjectMocks
    private SectionService sectionService;

    @Test
    void testGetSectionById_ResultSuccess() {
        long sectionId = 1L;
        Section section = new Section();
        SectionLongDto expected = new SectionLongDto();
        section.setJoinedUsers(new HashSet<>());

        when(sectionValidator.validateExists(sectionId)).thenReturn(section);
        when(mapperService.mapToSectionLongDto(section)).thenReturn(expected);

        assertNotNull(section);
        SectionLongDto result = sectionService.getSectionById(sectionId);

        assertEquals(expected, result);
        verify(sectionValidator).validateExists(sectionId);
        verify(mapperService).mapToSectionLongDto(section);
    }


    @Test
    void testGetSectionById_ResultNotFoundException() {
        long sectionId = 1L;
        String message = "Section not found";

        when(sectionValidator.validateExists(sectionId)).thenThrow(new NotFoundException(message));

        assertThrows(NotFoundException.class, () -> sectionService.getSectionById(sectionId));

        verify(sectionValidator).validateExists(sectionId);
        verify(mapperService, never()).mapToSectionLongDto(any());
    }

    @Test
    void testEditSection_ReturnUpdateSectionAndSaveToRepository() {
        long sectionId = 1L;
        StandardUser requester = new StandardUser();
        SectionLongDto sectionDto = new SectionLongDto();
        Section section = new Section();
        Section sectionFromDto = new Section();
        when(sectionValidator.validateExists(sectionId)).thenReturn(section);
        when(mapperService.mapToSection(sectionDto)).thenReturn(sectionFromDto);

        sectionService.editSection(sectionId, sectionDto, requester);

        verify(sectionValidator).validateExists(sectionId);
        verify(sectionValidator).validateUserAuthorized(section, requester);
        verify(mapperService).mapToSection(sectionDto);
        verify(sectionValidator).validateNotActiveDuplicate(section, sectionFromDto.getName());
        verify(sectionValidator).validateOwner(section, sectionFromDto.getGroupOwner());
        verify(sectionValidator).validateInput(section);
        verify(sectionRepo).save(section);
    }
    @Test
    void updateFields_ReturnUpdateSectionFields() {
        Section section = new Section("section1");
        section.setGroupOwner(new StandardUser(1L, "user1"));
        section.setDescription("description1");

        Section sectionFromDto = new Section("section2");
        sectionFromDto.setGroupOwner(new StandardUser(2L, "user2"));
        sectionFromDto.setDescription("description2");

        sectionService.updateFields(section, sectionFromDto);

        assertEquals(section.getName(), sectionFromDto.getName());
        assertEquals(section.getGroupOwner(), sectionFromDto.getGroupOwner());
        assertEquals(section.getDescription(), sectionFromDto.getDescription());
    }



    @Test
    public void setCountMembers_ReturnEmptyListForEmptySectionsList() {
        List<Section> sections = new ArrayList<>();

        List<Section> result = sectionService.setCountMembers(sections);

        assertEquals(0, result.size());
    }

    @Test
    public void setCountMembers_ReturnSectionsListWithCorrectMembersCount() {
        Set<StandardUser> joinedUsers1 = new HashSet<>();
        joinedUsers1.add(new StandardUser(1L, "user1"));
        joinedUsers1.add(new StandardUser(2L, "user2"));
        Section section1 = new Section("Section 1");
        section1.setJoinedUsers(joinedUsers1);

        Set<StandardUser> joinedUsers2 = new HashSet<>();
        joinedUsers2.add(new StandardUser(3L, "user3"));
        joinedUsers2.add(new StandardUser(4L, "user4"));
        joinedUsers2.add(new StandardUser(5L, "user5"));
        Section section2 = new Section("Section 2");
        section2.setJoinedUsers(joinedUsers2);

        List<Section> sections = List.of(section1, section2);

        List<Section> result = sectionService.setCountMembers(sections);

        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getMembersCount());
        assertEquals(3, result.get(1).getMembersCount());
    }


    @Test
    public void testSetSectionOwnerByAdmin() {
        long newOwnerId = 1234L;
        long sectionId = 5678L;
        Section section = new Section();
        section.setGroupId(sectionId);
        StandardUser newOwner = new StandardUser();
        newOwner.setParticipantId(newOwnerId);

        when(sectionValidator.validateExists(sectionId)).thenReturn(section);
        when(sectionValidator.validateOwnerForAdmin(section, newOwnerId)).thenReturn(newOwner);

        sectionService.setSectionOwnerByAdmin(newOwnerId, sectionId);

        verify(sectionValidator).validateExists(sectionId);
        verify(sectionValidator).validateOwnerForAdmin(section, newOwnerId);
        verify(sectionRepo).updateOwner(section.getGroupId(), newOwner.getParticipantId());
    }
}