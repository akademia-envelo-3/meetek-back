package pl.envelo.meetek.domain.attachment;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.exceptions.NotFoundException;
import pl.envelo.meetek.utils.ValidatorService;

import java.util.Optional;

@Service
public class AttachmentValidator extends ValidatorService<Attachment> {

    private final AttachmentRepo attachmentRepo;

    public AttachmentValidator(Validator validator, AttachmentRepo attachmentRepo) {
        super(validator);
        this.attachmentRepo = attachmentRepo;
    }

    @Override
    public Attachment validateExists(long id) {
        Optional<Attachment> attachment = attachmentRepo.findById(id);
        if (attachment.isEmpty()) {
            throw new NotFoundException("Attachment with id " + id + " not found");
        }
        return attachment.get();
    }

}
