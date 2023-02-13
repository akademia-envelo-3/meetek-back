package pl.envelo.meetek.domain.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.group.model.Section;
import pl.envelo.meetek.domain.group.model.SectionCreateDto;
import pl.envelo.meetek.domain.group.model.SectionLongDto;
import pl.envelo.meetek.domain.group.model.SectionShortDto;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.List;

@AllArgsConstructor
@Service
public class SectionService {

    private final SectionRepo sectionRepo;
    private final SectionValidator sectionValidator;
    private final DtoMapperService mapperService;

    @Transactional(readOnly = true)
    public SectionLongDto getSectionById(long id) {
        Section section = sectionValidator.validateExists(id);
        Section sectionWithMembersCount = setCountMembers(section);
        return mapperService.mapToSectionLongDto(sectionWithMembersCount);
    }

    @Transactional
    public SectionShortDto createSection(StandardUser user, SectionCreateDto sectionDto) {
        Section section = mapperService.mapToSection(sectionDto);
        sectionValidator.validateNotActiveDuplicate(section.getName());
        section.setGroupOwner(user);
        section.setIsActive(true);
        sectionValidator.validateInput(section);
        section = sectionRepo.save(section);
        return mapperService.mapToSectionShortDto(section);
    }

    @Transactional
    public void editSection(long sectionId, SectionLongDto sectionDto, StandardUser requester) {
        Section section = sectionValidator.validateExists(sectionId);
        sectionValidator.validateUserAuthorized(section, requester);
        Section sectionFromDto = mapperService.mapToSection(sectionDto);
        sectionValidator.validateNotActiveDuplicate(section, sectionFromDto.getName());
        sectionFromDto.setGroupOwner(sectionValidator.validateOwner(section, sectionFromDto.getGroupOwner()));
        updateFields(section, sectionFromDto);
        sectionValidator.validateInput(section);
        sectionRepo.save(section);
    }

    @Transactional(readOnly = true)
    public List<SectionShortDto> getAllActiveSections() {
        List<Section> sections = sectionRepo.findAllByIsActiveTrueOrderByName();
        List<Section> sectionsWithMembersCount = setCountMembers(sections);
        return sectionsWithMembersCount.stream()
                .map(mapperService::mapToSectionShortDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SectionShortDto> getAllJoinedSections(long userId) {
        List<Section> sections = sectionRepo.findAllJoinedSections(userId);
        List<Section> sectionsWithMembersCount = setCountMembers(sections);
        return sectionsWithMembersCount.stream()
                .map(mapperService::mapToSectionShortDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SectionShortDto> getAllOwnedSectionsByUserId(long userId) {
        List<Section> sections = sectionRepo.findAllOwnedSections(userId);
        List<Section> sectionsWithMembersCount = setCountMembers(sections);
        return sectionsWithMembersCount.stream()
                .map(mapperService::mapToSectionShortDto)
                .toList();
    }

    @Transactional
    public void setSectionOwnerByAdmin(long newOwnerId, long sectionId) {
        Section section = sectionValidator.validateExists(sectionId);
        StandardUser newOwner = sectionValidator.validateOwnerForAdmin(section, newOwnerId);
        sectionRepo.updateOwner(section.getGroupId(), newOwner.getParticipantId());
    }

    private Section setCountMembers(Section section) {
        section.setMembersCount(section.getJoinedUsers().size());
        return section;
    }

    private List<Section> setCountMembers(List<Section> sections) {
        return sections.stream().map(this::setCountMembers).toList();
    }

    private void updateFields(Section section, Section sectionFromDto) {
        section.setGroupOwner(sectionFromDto.getGroupOwner());
        section.setName(sectionFromDto.getName());
        section.setDescription(sectionFromDto.getDescription());
    }

}

