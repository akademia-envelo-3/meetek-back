package pl.envelo.meetek.service.attachment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.model.attachment.Attachment;
import pl.envelo.meetek.repository.attachment.AttachmentRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AttachmentService {

    private final AttachmentRepo attachmentRepo;

    @Transactional
    public Attachment saveAttachment(Attachment attachment) {
        return attachmentRepo.save(attachment);
    }
    @Transactional(readOnly = true)
    public Optional<Attachment> getAttachmentById(long attachmentId) {
        return attachmentRepo.findById(attachmentId);
    }
    @Transactional
    public void deleteAttachmentById(long attachmentId) {
        attachmentRepo.deleteById(attachmentId);
    }

}
