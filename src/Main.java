import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {

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

    public static void main(String[] args) {
        List<String> displayTokens = PritheeUtils.tokenizeForDisplay(SONNET);
        List<String> answerTokens = PritheeUtils.tokenizeForAnswer(SONNET);

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int correct = 0;
        int incorrect = 0;
        int lastIndex = -1;

        while (correct < 3 && incorrect < 3) {
            int missingIndex =
                    PritheeUtils.pickNewIndex(random, answerTokens.size(), lastIndex);
            lastIndex = missingIndex;

            System.out.println(
                    PritheeUtils.buildPrompt(displayTokens, answerTokens, missingIndex)
            );
            System.out.print("Next word: ");

            String guess =
                    PritheeUtils.normalize(scanner.nextLine());
            String answer = answerTokens.get(missingIndex);

            if (guess.equals(answer)) {
                correct++;
                System.out.println("Correct (" + correct + " correct, " + incorrect + " incorrect)");
            } else {
                incorrect++;
                System.out.println(
                        "Incorrect. Correct word was: " + answer +
                                " (" + correct + " correct, " + incorrect + " incorrect)"
                );
            }

            System.out.println();
        }

        if (correct >= 3) {
            System.out.println("You win: three correct answers.");
        } else {
            System.out.println("Game over: three incorrect answers.");
        }

        scanner.close();
    }
}
