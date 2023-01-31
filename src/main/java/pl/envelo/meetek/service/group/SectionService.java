package pl.envelo.meetek.service.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.dto.group.SectionLongDto;
import pl.envelo.meetek.model.group.Section;
import pl.envelo.meetek.repository.group.SectionRepo;
import pl.envelo.meetek.service.user.StandardUserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SectionService {

    private final SectionRepo sectionRepo;
    private final StandardUserService standardUserService;

    @Transactional(readOnly = true)
    public List<Section> getAllActiveSections() {
        List<Section> sections = sectionRepo.findAllByIsActiveTrueOrderByName();
        return setCountMembers(sections);
    }

    @Transactional(readOnly = true)
    public List<Section> getOwnedSectionsByUserId(long userId) {
        List<Section> sections = sectionRepo.findAllOwnedSections(userId);
        return setCountMembers(sections);
    }

    @Transactional(readOnly = true)
    public Optional<Section> getSectionById(long id) {
        Optional<Section> section = sectionRepo.findById(id);
        return section.map(this::setCountMembers);
    }

    @Transactional
    public Section editSection(Section section, SectionLongDto sectionDto) {
        section.setName(sectionDto.getName());
        section.setDescription(sectionDto.getDescription());
        section.setActive(sectionDto.isActive());
        if (sectionDto.getGroupOwner().getParticipantId() != sectionDto.getGroupOwner().getParticipantId()) {
            if (standardUserService.getStandardUserById(sectionDto.getGroupOwner().getParticipantId()).isPresent()) {
                section.setGroupOwner(standardUserService.getStandardUserById(sectionDto.getGroupOwner().getParticipantId()).get());
            }
        }
        return sectionRepo.save(section);
    }

    @Transactional(readOnly = true)
    public List<Section> getAllJoinedSections(long userId) {
        List<Section> sections = sectionRepo.findAllJoinedSections(userId);
        return setCountMembers(sections);
    }

    @Transactional
    public Section saveNewSection(Section section) {
        return sectionRepo.save(section);
    }

    private Section setCountMembers(Section section) {
        section.setMembersCount(section.getJoinedUsers().size());
        return section;
    }

    private List<Section> setCountMembers(List<Section> sections) {
        return sections.stream().map(this::setCountMembers).toList();
    }

}

