package pl.envelo.meetek.domain.attachment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class AttachmentService {

    private final AttachmentRepo attachmentRepo;
    private final AttachmentValidator attachmentValidator;

    @Transactional
    public Attachment createAttachment(Attachment attachment) {
        attachmentValidator.validateInput(attachment);
        return attachmentRepo.save(attachment);
    }
    @Transactional(readOnly = true)
    public Attachment getAttachmentById(long attachmentId) {
        return attachmentValidator.validateExists(attachmentId);
    }
    @Transactional
    public void deleteAttachmentById(long attachmentId) {
        attachmentRepo.deleteById(attachmentId);
    }

}
