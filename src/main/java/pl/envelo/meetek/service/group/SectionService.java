package pl.envelo.meetek.service.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.group.Section;
import pl.envelo.meetek.repository.group.SectionRepo;

import java.util.List;

@AllArgsConstructor
@Service
public class SectionService {

    private final SectionRepo sectionRepo;

    public List<Section> getAllActiveSections() {
        return sectionRepo.findAllByIsActiveOrderByName(true);
    }

}

