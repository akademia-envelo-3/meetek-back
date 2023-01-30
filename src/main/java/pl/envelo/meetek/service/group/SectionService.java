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
        return sectionRepo.findAllByIsActiveTrueOrderByName();
    }

    @Transactional(readOnly = true)
    public List<Section> getOwnedSectionsByUserId(long userId) {
        return sectionRepo.findAllOwnedSections(userId);
    }

    @Transactional(readOnly = true)
    public Optional<Section> getSectionById(long id) {
        return sectionRepo.findById(id);
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
        return sectionRepo.findAllJoinedSections(userId);
    }

    @Transactional
    public Section saveNewSection(Section section) {
        return sectionRepo.save(section);
    }

}

