package pl.envelo.meetek.domain.category;

import org.junit.jupiter.api.Test;
import pl.envelo.meetek.domain.category.Category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryTest {

    @Test
    void testEquals() {
        Category category1 = new Category("TestCategory1");
        Category category2 = new Category("TestCategory2");
        Category category3 = new Category("TestCategory1");

        assertEquals(false, category1.equals(category2));
        assertEquals(true, category1.equals(category3));
    }

    @Test
    void testHashCode() {
        Category category1 = new Category("TestCategory1");
        Category category2 = new Category("TestCategory2");

        assertEquals(false, category1.hashCode() == category2.hashCode());
    }

    @Test
    void testToString() {
        Category category = mock(Category.class);
        when(category.toString()).thenReturn("Category{categoryId=1, name='TestCategory', isActive=true}");

        assertEquals("Category{categoryId=1, name='TestCategory', isActive=true}", category.toString());
    }
}

