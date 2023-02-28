package pl.envelo.meetek.integration.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.group.SectionRepo;
import pl.envelo.meetek.domain.group.SectionService;
import pl.envelo.meetek.domain.user.StandardUserRepo;

@SpringBootTest
@Transactional
public class SectionServiceIntegrationTest {

    @Autowired
    SectionService sectionService;

    @Autowired
    SectionRepo sectionRepo;

    @Autowired
    StandardUserRepo userRepo;


}
