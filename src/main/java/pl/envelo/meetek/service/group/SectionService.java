package pl.envelo.meetek.service.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.group.SectionRepo;

@AllArgsConstructor
@Service
public class SectionService {

    private final SectionRepo sectionRepo;
}

