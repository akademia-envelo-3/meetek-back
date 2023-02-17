package pl.envelo.meetek.domain.group;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.domain.group.model.Section;
import pl.envelo.meetek.domain.user.StandardUserRepo;
import pl.envelo.meetek.domain.user.StandardUserValidator;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.exceptions.DuplicateException;
import pl.envelo.meetek.exceptions.NotAuthorizedUserException;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.exceptions.ProcessingException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class SectionValidator extends ValidatorService<Section> {

    private final SectionRepo sectionRepo;
    private final StandardUserValidator userValidator;

    public SectionValidator(Validator validator, SectionRepo sectionRepo, StandardUserRepo userRepo,StandardUserValidator userValidator) {
        super(validator);
        this.sectionRepo = sectionRepo;
        this.userValidator = userValidator;
    }

    @Override
    public Section validateExists(long id) {
        Optional<Section> section = sectionRepo.findById(id);
        if (section.isEmpty()) {
            throw new NotFoundException("Section with id " + id + " not found");
        }
        return section.get();
    }

    public void validateNotActiveDuplicate(String name) {
        Optional<Section> section = sectionRepo.findByName(name);
        if (section.isPresent() && section.get().getIsActive()) {
            throw new DuplicateException("Section with name " + name + " already exists");
        }

    }

    public void validateNotActiveDuplicate(Section section, String name) {
        if (!section.getName().equals(name)) {
            Optional<Section> sectionFromDto = sectionRepo.findByName(name);
            if (sectionFromDto.isPresent() && sectionFromDto.get().getIsActive()) {
                throw new DuplicateException("Section with name " + name + " already exists");
            }
        }
    }

    public StandardUser validateOwner(Section section, StandardUser newOwner) {
        if (newOwner == null || section.getGroupOwner().getParticipantId().equals(newOwner.getParticipantId())) {
            return section.getGroupOwner();
        }
        userValidator.validateExists(newOwner.getParticipantId());
        return newOwner;
    }

    public void validateUserAuthorized(Section section, StandardUser user) {
        if (!section.getGroupOwner().getParticipantId().equals(user.getParticipantId())) {
            throw new NotAuthorizedUserException("Only section owner can modify section");
        }
    }

    public StandardUser validateOwnerForAdmin(Section section, long newOwnerId){
        if(section.getGroupOwner().getParticipantId().equals(newOwnerId)){
            throw new DuplicateException("User with id " + newOwnerId + " is already the owner of this section");
        }
        return userValidator.validateExists(newOwnerId);
    }

    public void validateActive(Section section) {
        if (!section.getIsActive()) {
            throw new ProcessingException("Section is not active");
        }
    }
    public void validateUserNotMember(StandardUser user, Section section) {
        if (section.getJoinedUsers().contains(user)) {
            throw new ProcessingException("User is already a member of the section " + section.getName());
        }
    }

    public void validateUserMember(StandardUser user, Section section) {
        if (!section.getJoinedUsers().contains(user)) {
            throw new ProcessingException("User is not a member of the section " + section.getName());
        }
    }

}
