package pl.envelo.meetek.domain.attachment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttachmentTest {

    @Test
    void testToString() {
        Attachment attachment = mock(Attachment.class);
        when(attachment.toString()).thenReturn("Attachment{attachmentId=1, link='http://example.com'}");

        assertEquals("Attachment{attachmentId=1, link='http://example.com'}", attachment.toString());
    }

    @Test
    void testEquals() {
        Attachment attachment1 = new Attachment(1L, "http://example.com");
        Attachment attachment2 = new Attachment(2L, "http://example.com");
        Attachment attachment3 = new Attachment(1L, "http://example.com");

        assertEquals(false, attachment1.equals(attachment2));
        assertEquals(true, attachment1.equals(attachment3));
    }

    @Test
    void testHashCode() {
        Attachment attachment1 = new Attachment(1L, "http://example.com");
        Attachment attachment2 = new Attachment(2L, "http://example.com");

        assertEquals(false, attachment1.hashCode() == attachment2.hashCode());
    }
}