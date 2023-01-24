package pl.envelo.meetek.service.attachment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.attachment.Attachment;
import pl.envelo.meetek.repository.attachment.AttachmentRepo;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AttachmentService {

    private final AttachmentRepo attachmentRepo;

    public Attachment saveAttachment(Attachment attachment) {
        return attachmentRepo.save(attachment);
    }

    public Optional<Attachment> getAttachmentById(long attachmentId) {
        return attachmentRepo.findById(attachmentId);
    }

    public void deleteAttachmentById(long attachmentId) {
        attachmentRepo.deleteById(attachmentId);
    }

}
