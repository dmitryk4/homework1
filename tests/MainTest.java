import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for Homework 1 (Prithee).
 *
 * These tests focus on deterministic helper methods that contain
 * algorithmic logic and can be tested independently of user input.
 */
public class MainTest {

    /**
     * Tests that normalize() lowercases words and removes punctuation.
     */
    @Test
    public void testNormalizeBasicCases() {
        assertEquals("verona", PritheeUtils.normalize("Verona,"));
        assertEquals("dignity", PritheeUtils.normalize("dignity."));
        assertEquals("unclean", PritheeUtils.normalize("unclean!"));
        assertEquals("love", PritheeUtils.normalize("LOVE"));
    }

    /**
     * Tests that normalize() correctly handles curly apostrophes
     * used in the sonnet text.
     */
    @Test
    public void testNormalizeCurlyApostrophe() {
        assertEquals("star-cross'd", PritheeUtils.normalize("star-cross’d"));
        assertEquals("death-mark'd", PritheeUtils.normalize("death-mark’d"));
    }

    /**
     * Tests that pickNewIndex() does not return the same index twice in a row.
     */
    @Test
    public void testPickNewIndexAvoidsRepeat() {
        Random random = new Random(42);
        int lastIndex = 5;
        int size = 20;

        int newIndex = PritheeUtils.pickNewIndex(random, size, lastIndex);

        assertNotEquals(lastIndex, newIndex);
    }

    /**
     * Tests that pickNewIndex() behaves correctly when only one index exists.
     */
    @Test
    public void testPickNewIndexSingleElement() {
        Random random = new Random(42);

        assertEquals(0, PritheeUtils.pickNewIndex(random, 1, 0));
        assertEquals(0, PritheeUtils.pickNewIndex(random, 1, 10));
    }
}
