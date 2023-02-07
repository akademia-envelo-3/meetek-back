package pl.envelo.meetek.domain.attachment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
