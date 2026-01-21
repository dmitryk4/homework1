import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Utility methods for the Prithee game.
 *
 * Contains all non-interactive logic so it can be tested independently.
 */
public class PritheeUtils {

    /**
     * Splits the sonnet into tokens for display.
     * Punctuation is preserved.
     */
    static List<String> tokenizeForDisplay(String text) {
        String[] parts = text.trim().split("\\s+");
        return Arrays.asList(parts);
    }

    /**
     * Splits the sonnet into normalized tokens for answer checking.
     */
    static List<String> tokenizeForAnswer(String text) {
        String[] parts = text.trim().split("\\s+");
        List<String> tokens = new ArrayList<>();

        for (String part : parts) {
            tokens.add(normalize(part));
        }

        return tokens;
    }

    /**
     * Normalizes a token by:
     * - lowercasing
     * - converting curly apostrophes to straight apostrophes
     * - stripping leading and trailing punctuation
     */
    static String normalize(String token) {
        String t = token.toLowerCase(Locale.ROOT);
        t = t.replace("’", "'");
        t = t.replaceAll("^[^a-z0-9']+|[^a-z0-9']+$", "");
        return t;
    }

    /**
     * Builds the prompt shown to the user.
     * Prints the sonnet up to the missing word,
     * replaces it with underscores, and stops.
     */
    static String buildPrompt(
            List<String> displayTokens,
            List<String> answerTokens,
            int missingIndex
    ) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < displayTokens.size(); i++) {
            if (i == missingIndex) {
                int length = Math.max(4, answerTokens.get(i).length());
                builder.append("_".repeat(length));
                break;
            } else {
                builder.append(displayTokens.get(i)).append(" ");
            }
        }

        return builder.toString().trim();
    }

    /**
     * Selects a random index that is not the same as the previous index.
     */
    static int pickNewIndex(Random random, int size, int lastIndex) {
        if (size <= 1) {
            return 0;
        }

        int index;
        do {
            index = random.nextInt(size);
        } while (index == lastIndex);

        return index;
    }
}
