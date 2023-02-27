package pl.envelo.meetek.domain.hashtag;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class HashtagTest {

    @Test
    void testToString() {
        Hashtag hashtag = new Hashtag(1L, "#springboot", true, 10);

        String expected = "Hashtag{" +
                "hashtagId=" + 1L +
                ", name='#springboot'" +
                ", isActive=true" +
                ", countOfHashtagUsage=10" +
                '}';
        assertThat(hashtag.toString()).isEqualTo(expected);
    }

    @Test
    void testEqualsAndHashCode() {
        Hashtag hashtag1 = new Hashtag(1L, "#springboot", true, 10);
        Hashtag hashtag2 = new Hashtag(1L, "#springboot", true, 10);

        assertThat(hashtag1).isEqualTo(hashtag2);
        assertThat(hashtag1.hashCode()).isEqualTo(hashtag2.hashCode());
    }

    @Test
    void testEqualsAndHashCode_WhenNullFields() {
        Hashtag hashtag1 = new Hashtag(1L, null, true, 10);
        Hashtag hashtag2 = new Hashtag(1L, null, true, 10);

        assertThat(hashtag1).isEqualTo(hashtag2);
        assertThat(hashtag1.hashCode()).isEqualTo(hashtag2.hashCode());
    }

    @Test
    void testNotEqualsAndHashCode_WhenDifferentFields() {
        Hashtag hashtag1 = new Hashtag(1L, "#springboot", true, 10);
        Hashtag hashtag2 = new Hashtag(2L, "#springboot", true, 10);

        assertThat(hashtag1).isNotEqualTo(hashtag2);
        assertThat(hashtag1.hashCode()).isNotEqualTo(hashtag2.hashCode());
    }

    @Test
    void testNotEqualsAndHashCode_WhenDifferentActiveFields() {
        Hashtag hashtag1 = new Hashtag(1L, "#springboot", true, 10);
        Hashtag hashtag2 = new Hashtag(1L, "#springboot", false, 10);

        assertThat(hashtag1).isNotEqualTo(hashtag2);
        assertThat(hashtag1.hashCode()).isNotEqualTo(hashtag2.hashCode());
    }

    @Test
    void testNotEqualsAndHashCode_WhenDifferentCountOfHashtagUsageFields() {
        Hashtag hashtag1 = new Hashtag(1L, "#springboot", true, 10);
        Hashtag hashtag2 = new Hashtag(1L, "#springboot", true, 20);

        assertThat(hashtag1).isNotEqualTo(hashtag2);
        assertThat(hashtag1.hashCode()).isNotEqualTo(hashtag2.hashCode());
    }

    @Test
    void testNotEqualsAndHashCode_WhenDifferentNameFields() {
        Hashtag hashtag1 = new Hashtag(1L, "#springboot", true, 10);
        Hashtag hashtag2 = new Hashtag(1L, "#hibernate", true, 10);

        assertThat(hashtag1).isNotEqualTo(hashtag2);
        assertThat(hashtag1.hashCode()).isNotEqualTo(hashtag2.hashCode());
    }
}