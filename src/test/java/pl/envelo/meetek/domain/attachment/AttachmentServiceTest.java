package pl.envelo.meetek.domain.attachment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import pl.envelo.meetek.exceptions.NotFoundException;

public class AttachmentServiceTest {

    @Mock
    private AttachmentRepo attachmentRepo;

    @Mock
    private AttachmentValidator attachmentValidator;

    @InjectMocks
    private AttachmentService attachmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAttachment() {
        Attachment attachment = new Attachment();
        when(attachmentRepo.save(attachment)).thenReturn(attachment);

        Attachment createdAttachment = attachmentService.createAttachment(attachment);

        verify(attachmentValidator).validateInput(attachment);
        verify(attachmentRepo).save(attachment);

        assertEquals(attachment, createdAttachment);
    }

    @Test
    public void testGetAttachmentById() {
        Attachment attachment = new Attachment();
        attachment.setAttachmentId(1L);
        when(attachmentValidator.validateExists(1L)).thenReturn(attachment);

        Attachment retrievedAttachment = attachmentService.getAttachmentById(1L);

        verify(attachmentValidator).validateExists(1L);

        assertEquals(attachment, retrievedAttachment);
    }

    @Test
    public void testCreateAttachmentWithValidationException() {
        Attachment attachment = new Attachment();
        doThrow(new DataIntegrityViolationException("Validation failed")).when(attachmentValidator).validateInput(attachment);

        try {
            attachmentService.createAttachment(attachment);
        } catch (DataIntegrityViolationException e) {
            assertEquals("Validation failed", e.getMessage());
        }

        verify(attachmentValidator).validateInput(attachment);
        verifyNoMoreInteractions(attachmentRepo);
    }
}