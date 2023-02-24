package pl.envelo.meetek.domain.comment;

import org.junit.jupiter.api.Test;
import pl.envelo.meetek.domain.comment.model.RequestComment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestCommentTest {

    @Test
    void testEquals() {
        RequestComment requestComment1 = new RequestComment();
        RequestComment requestComment2 = new RequestComment();

        assertEquals(true, requestComment1.equals(requestComment2));

    }

    @Test
    void testHashCode() {
        RequestComment requestComment1 = new RequestComment();
        RequestComment requestComment2 = new RequestComment();

        assertEquals(true, requestComment1.hashCode() == requestComment2.hashCode());
    }

    @Test
    void testToString() {
        RequestComment requestComment = mock(RequestComment.class);
        when(requestComment.toString()).thenReturn("RequestComment{requestCommentId=1, name='TestRequestComment', isActive=true}");

        assertEquals("RequestComment{requestCommentId=1, name='TestRequestComment', isActive=true}", requestComment.toString());
    }
}

