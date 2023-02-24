package pl.envelo.meetek.domain.comment;

import org.junit.jupiter.api.Test;
import pl.envelo.meetek.domain.comment.model.EventComment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventCommentTest {
    @Test
    void testEquals() {
        EventComment eventComment1 = new EventComment();
        EventComment eventComment2 = new EventComment();

        assertEquals(true, eventComment1.equals(eventComment2));
    }

    @Test
    void testHashCode() {
        EventComment eventComment1 = new EventComment();
        EventComment eventComment2 = new EventComment();

        assertEquals(true, eventComment1.hashCode() == eventComment2.hashCode());
    }

    @Test
    void testToString() {
        EventComment eventComment = mock(EventComment.class);
        when(eventComment.toString()).thenReturn("EventComment{eventCommentId=1, name='TestEventComment', isActive=true}");
        assertEquals("EventComment{eventCommentId=1, name='TestEventComment', isActive=true}", eventComment.toString());
    }
}

