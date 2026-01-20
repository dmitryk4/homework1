import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/**
 * Prithee game implementation.
 *
 * The program prints the opening sonnet of Romeo and Juliet,
 * replaces a randomly chosen word with underscores, and prompts
 * the user to supply the missing word.
 *
 * The game continues until the user gets three correct answers
 * or three incorrect answers.
 */
public class Main {

    /**
     * The full sonnet text used by the game.
     * Stored as a class-level constant so it is accessible
     * by all helper methods.
     */
    private static final String SONNET = """
Two households, both alike in dignity,
In fair Verona, where we lay our scene,
From ancient grudge break to new mutiny,
Where civil blood makes civil hands unclean.
From forth the fatal loins of these two foes
A pair of star-cross’d lovers take their life;
Whose misadventured piteous overthrows
Do with their death bury their parents’ strife.
The fearful passage of their death-mark’d love,
And the continuance of their parents’ rage,
Which, but their children’s end, nought could remove,
Is now the two hours’ traffic of our stage;
The which if you with patient ears attend,
What here shall miss, our toil shall strive to mend.
""";

    /**
     * Program entry point.
     */
    public static void main(String[] args) {
        List<String> displayTokens = tokenizeForDisplay(SONNET);
        List<String> answerTokens = tokenizeForAnswer(SONNET);

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int correctCount = 0;
        int incorrectCount = 0;
        int lastIndex = -1;

        // Continue until the user reaches three correct or three incorrect answers
        while (correctCount < 3 && incorrectCount < 3) {
            int missingIndex = pickNewIndex(random, answerTokens.size(), lastIndex);
            lastIndex = missingIndex;

            // Print the sonnet prompt with one missing word
            System.out.println(buildPrompt(displayTokens, answerTokens, missingIndex));
            System.out.print("Next word: ");

            String userInput = scanner.nextLine();
            String normalizedGuess = normalize(userInput);
            String correctAnswer = answerTokens.get(missingIndex);

            if (normalizedGuess.equals(correctAnswer)) {
                correctCount++;
                System.out.println("Correct (" + correctCount + " correct, " + incorrectCount + " incorrect)");
            } else {
                incorrectCount++;
                System.out.println(
                        "Incorrect. Correct word was: " + correctAnswer +
                                " (" + correctCount + " correct, " + incorrectCount + " incorrect)"
                );
            }

            System.out.println();
        }

        // Final result
        if (correctCount >= 3) {
            System.out.println("You win: three correct answers.");
        } else {
            System.out.println("Game over: three incorrect answers.");
        }

        scanner.close();
    }

    /**
     * Splits the sonnet into tokens used for display.
     * Punctuation is preserved so the printed text
     * remains faithful to the original poem.
     */
    private static List<String> tokenizeForDisplay(String text) {
        String[] parts = text.trim().split("\\s+");
        return Arrays.asList(parts);
    }

    /**
     * Splits the sonnet into normalized tokens used for answer checking.
     * Tokens are lowercased and stripped of leading and trailing punctuation.
     */
    private static List<String> tokenizeForAnswer(String text) {
        String[] parts = text.trim().split("\\s+");
        List<String> tokens = new ArrayList<>();

        for (String part : parts) {
            tokens.add(normalize(part));
        }

        return tokens;
    }

    /**
     * Normalizes a word for comparison by:
     * - converting to lowercase
     * - converting curly apostrophes to straight apostrophes
     * - removing leading and trailing punctuation
     */
    private static String normalize(String token) {
        String t = token.toLowerCase(Locale.ROOT);
        t = t.replace("’", "'");
        t = t.replaceAll("^[^a-z0-9']+|[^a-z0-9']+$", "");
        return t;
    }

    /**
     * Builds the prompt shown to the user.
     * Prints the sonnet up to the missing word,
     * replaces that word with underscores, and stops.
     */
    private static String buildPrompt(
            List<String> displayTokens,
            List<String> answerTokens,
            int missingIndex
    ) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < displayTokens.size(); i++) {
            if (i == missingIndex) {
                int underscoreLength = Math.max(4, answerTokens.get(i).length());
                builder.append("_".repeat(underscoreLength));
                break;
            } else {
                builder.append(displayTokens.get(i));
                builder.append(" ");
            }
        }

        return builder.toString().trim();
    }

    /**
     * Selects a new random index that is different
     * from the previously selected index.
     */
    private static int pickNewIndex(Random random, int size, int lastIndex) {
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
