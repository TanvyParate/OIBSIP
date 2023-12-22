import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 5;

        System.out.println("Welcome to Guess the Number!");
        int totalScore = playGame(scanner, random, lowerBound, upperBound, maxAttempts);

        System.out.println("Your final score: " + totalScore);
        System.out.println("Thanks for playing!");

        scanner.close();
    }

    private static int playGame(Scanner scanner, Random random, int lowerBound, int upperBound, int maxAttempts) {
        int totalScore = 0;
        boolean playAgain = true;

        while (playAgain) {
            int randomNumber = generateRandomNumber(random, lowerBound, upperBound);
            System.out.println("I have generated a number between " + lowerBound + " and " + upperBound);

            int attempts = 0;

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    int roundScore = calculateScore(attempts);
                    totalScore += roundScore;
                    System.out.println("Round score: " + roundScore);
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        return totalScore;
    }

    private static int generateRandomNumber(Random random, int lowerBound, int upperBound) {
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    private static int calculateScore(int attempts) {
        // You can customize the scoring logic based on the number of attempts
        // For example, you can give more points for fewer attempts.
        return 100 - (attempts - 1) * 10;
    }
}
