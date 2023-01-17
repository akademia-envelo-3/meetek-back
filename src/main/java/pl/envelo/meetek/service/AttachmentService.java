package pl.envelo.meetek.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.AttachmentRepo;

@AllArgsConstructor
@Service
public class AttachmentService {

    private final AttachmentRepo attachmentRepo;
}
