package pl.envelo.meetek.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.model.Attachment;
import pl.envelo.meetek.repository.AttachmentRepo;

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
