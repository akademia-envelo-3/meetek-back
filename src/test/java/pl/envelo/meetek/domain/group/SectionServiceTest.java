package pl.envelo.meetek.domain.group;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.envelo.meetek.domain.group.model.Section;
import pl.envelo.meetek.domain.group.model.SectionLongDto;
import pl.envelo.meetek.domain.group.model.SectionShortDto;

import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SectionServiceTest {

    @Mock
    private SectionRepo sectionRepo;

    @Mock
    private SectionValidator sectionValidator;

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

    //Tests to create Section: Success and Failure
    @Test
    void testCreateSection_ResultSuccess() {
        Section section = new Section();
        SectionLongDto sectionLongDto = new SectionLongDto();
        sectionLongDto.setName("Section 1");
        section.setGroupOwner(new StandardUser());

        section.setName("Section 1");

        when(mapperService.mapToSection(sectionLongDto)).thenReturn(section);
        doNothing().when(sectionValidator).validateInput(section);
        doNothing().when(sectionValidator).validateNotActiveDuplicate(section.getName());

        when(sectionRepo.save(section)).thenReturn(section);
        when(mapperService.mapToSectionLongDto(section)).thenReturn(sectionLongDto);

        //    SectionLongDto result = sectionService.createSection(StandardUser,sectionLongDto);

        //    assertNotNull(result);
        //  assertEquals("Section 1", result.getName());
    }

    //edit
  /*
   @Test
    public void testEditSection_ResultFailure() {
        // Arrange
        long sectionId = 1L;
        SectionLongDto sectionLongDto = new SectionLongDto();
        Section section = new Section();
        Section sectionFromDto = new Section();
        StandardUser user = new StandardUser();
        user.setParticipantId(1L);
        section.setGroupOwner(user);

        when(sectionValidator.validateExists(sectionId)).thenReturn(section);
        when(mapperService.mapToSection(sectionLongDto)).thenReturn(sectionFromDto);
        doThrow(IllegalArgumentException.class).when(sectionValidator).validateInput(sectionFromDto);
        sectionService.editSection(sectionId, sectionLongDto,user);
//        assertThrows(IllegalArgumentException.class, () -> sectionService.editSection(sectionId, sectionLongDto,user));
    }
    */
    //active
    @Test
    public void testGetAllActiveSectionsAndActiveSections_WhenFound() {
        Section section = new Section();
        Section section1 = new Section();
        Section section2 = new Section();
        List<Section> sections = Arrays.asList(section1, section2);


        SectionLongDto sectionLongDto1 = new SectionLongDto();
        SectionLongDto sectionLongDto2 = new SectionLongDto();
        List<SectionLongDto> sectionLongDtos = Arrays.asList(sectionLongDto1, sectionLongDto2);

        when(sectionRepo.findAllByIsActiveTrueOrderByName()).thenReturn(sections);
        when(mapperService.mapToSectionLongDto(any(Section.class))).thenReturn(sectionLongDto1, sectionLongDto2);

        List<SectionShortDto> result = sectionService.getAllActiveSections();

        assertEquals(sectionLongDtos, result);
        verify(sectionRepo).findAllByIsActiveTrueOrderByName();
        verify(mapperService).mapToSectionLongDto(section1);
        verify(mapperService).mapToSectionLongDto(section2);
    }
}
    /*
    @Test
    public void testGetAllActiveCategories_ReturnFailure() {
        CategoryService categoryService = new CategoryService(categoryRepo, categoryValidator, mapperService);

        when(categoryRepo.findAllByIsActiveTrueOrderByName()).thenThrow(new RuntimeException("Error in repository"));

        Exception exception = assertThrows(RuntimeException.class,
                () -> categoryService.getAllActiveCategories());

        assertEquals("Error in repository", exception.getMessage())
}

*/