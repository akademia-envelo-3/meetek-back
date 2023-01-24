package pl.envelo.meetek.service.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.dto.group.SectionLongDto;
import pl.envelo.meetek.model.group.Section;
import pl.envelo.meetek.repository.group.SectionRepo;
import pl.envelo.meetek.service.user.StandardUserService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SectionService {

    private final SectionRepo sectionRepo;
    private final StandardUserService standardUserService;

    public List<Section> getAllActiveSections() {
        return sectionRepo.findAllByIsActiveOrderByName(true);
    }

    public Optional<Section> getSectionById(long id) {
        return sectionRepo.findById(id);
    }

    public Section editSection(Section section, SectionLongDto sectionDto) {
        section.setName(sectionDto.getName());
        section.setDescription(sectionDto.getDescription());
        section.setActive(sectionDto.isActive());
        if (sectionDto.getSectionOwner().getParticipantId() != sectionDto.getSectionOwner().getParticipantId()) {
            if (standardUserService.getStandardUserById(sectionDto.getSectionOwner().getParticipantId()).isPresent()) {
                section.setSectionOwner(standardUserService.getStandardUserById(sectionDto.getSectionOwner().getParticipantId()).get());
            }
        }
        return sectionRepo.save(section);
    }

    public List<Section> getAllJoinedSections(long userId) {
        return sectionRepo.findAllJoinedSections(userId);
    }

}

