package pl.envelo.meetek.domain.group;

import org.junit.jupiter.api.Test;
import pl.envelo.meetek.domain.group.model.Section;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SectionTest {
    @Test
    void testEquals() {
        Section section1 = new Section();
        Section section2 = new Section();
        section1.setGroupId(1L);

        assertEquals(false, section1.equals(section2));

    }

    @Test
    void testHashCode() {
        Section section1 = new Section();
        Section section2 = new Section();

        assertEquals(true, section1.hashCode() == section2.hashCode());
    }

    @Test
    void testToString() {
        Section section = mock(Section.class);
        when(section.toString()).thenReturn("Section{sectionId=1, name='TestSection', isActive=true}");

        assertEquals("Section{sectionId=1, name='TestSection', isActive=true}", section.toString());
    }
}

