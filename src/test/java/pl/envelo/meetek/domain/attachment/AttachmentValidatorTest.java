package pl.envelo.meetek.domain.attachment;

import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.envelo.meetek.domain.attachment.Attachment;
import pl.envelo.meetek.domain.attachment.AttachmentRepo;
import pl.envelo.meetek.domain.attachment.AttachmentValidator;
import pl.envelo.meetek.exceptions.NotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class AttachmentValidatorTest {

    @Mock
    private Validator validator;

    @Mock
    private AttachmentRepo attachmentRepo;

    private AttachmentValidator attachmentValidator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        attachmentValidator = new AttachmentValidator(validator, attachmentRepo);
    }

    @Test
    public void validateExists_attachmentNotFound_throwNotFoundException() {
        long id = 1L;
        when(attachmentRepo.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> attachmentValidator.validateExists(id));
        assertEquals("Attachment with id 1 not found", exception.getMessage());
    }

    @Test
    public void validateExists_attachmentFound_returnAttachment() {
        long id = 1L;
        Attachment attachment = new Attachment();
        when(attachmentRepo.findById(id)).thenReturn(Optional.of(attachment));

        Attachment result = attachmentValidator.validateExists(id);
        assertEquals(attachment, result);
    }

}