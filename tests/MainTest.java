import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for Homework 1 (Prithee).
 *
 * Tests focus on deterministic helper methods in PritheeUtils.
 */
public class MainTest {

    @Test
    public void testTokenizeForDisplay() {
        String text = "Hello, world!";
        List<String> tokens = PritheeUtils.tokenizeForDisplay(text);

        assertEquals(2, tokens.size());
        assertEquals("Hello,", tokens.get(0));
        assertEquals("world!", tokens.get(1));
    }

    @Test
    public void testTokenizeForAnswer() {
        String text = "Hello, world!";
        List<String> tokens = PritheeUtils.tokenizeForAnswer(text);

        assertEquals(2, tokens.size());
        assertEquals("hello", tokens.get(0));
        assertEquals("world", tokens.get(1));
    }

    @Test
    public void testNormalizeLowercasesAndStripsPunctuation() {
        assertEquals("verona", PritheeUtils.normalize("Verona,"));
        assertEquals("dignity", PritheeUtils.normalize("dignity."));
        assertEquals("unclean", PritheeUtils.normalize("unclean!"));
        assertEquals("love", PritheeUtils.normalize("LOVE"));
    }

    @Test
    public void testNormalizeCurlyApostrophe() {
        assertEquals("star-cross'd", PritheeUtils.normalize("star-cross’d"));
        assertEquals("death-mark'd", PritheeUtils.normalize("death-mark’d"));
    }

    @Test
    public void testBuildPrompt() {
        List<String> displayTokens = Arrays.asList("one", "two", "three", "four");
        List<String> answerTokens = Arrays.asList("one", "two", "three", "four");

        int missingIndex = 2;
        String prompt = PritheeUtils.buildPrompt(displayTokens, answerTokens, missingIndex);

        // Words before the missing index should be printed
        assertTrue(prompt.contains("one"));
        assertTrue(prompt.contains("two"));

        // The missing word and all words after it should not be printed
        assertFalse(prompt.contains("three"));
        assertFalse(prompt.contains("four"));

        // Underscores should appear in place of the missing word
        assertTrue(prompt.contains("_"));
    }

    @Test
    public void testPickNewIndexAvoidsImmediateRepeat() {
        Random random = new Random(42);
        int size = 20;
        int lastIndex = 7;

        int newIndex = PritheeUtils.pickNewIndex(random, size, lastIndex);

        assertNotEquals(lastIndex, newIndex);
        assertTrue(newIndex >= 0 && newIndex < size);
    }

    @Test
    public void testPickNewIndexSingleElementAlwaysZero() {
        Random random = new Random(42);

        assertEquals(0, PritheeUtils.pickNewIndex(random, 1, 0));
        assertEquals(0, PritheeUtils.pickNewIndex(random, 1, 10));
    }
}
