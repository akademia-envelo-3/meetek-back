package pl.envelo.meetek.service.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.dto.group.SectionCreateDto;
import pl.envelo.meetek.dto.group.SectionLongDto;
import pl.envelo.meetek.model.group.Section;
import pl.envelo.meetek.model.user.StandardUser;
import pl.envelo.meetek.repository.group.SectionRepo;
import pl.envelo.meetek.service.user.StandardUserService;

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
    public Section editSection(Section sectionToUpdate, Section sectionBody) {
        sectionToUpdate.setName(sectionBody.getName());
        sectionToUpdate.setDescription(sectionBody.getDescription());
        return sectionRepo.save(sectionToUpdate);
    }

    @Transactional(readOnly = true)
    public List<Section> getAllJoinedSections(long userId) {
        List<Section> sections = sectionRepo.findAllJoinedSections(userId);
        return setCountMembers(sections);
    }

    @Transactional
    public Section saveNewSection(StandardUser standardUser, Section section) {

        section.setGroupOwner(standardUser);
        section.setActive(true);
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

