package com.java.game;

import java.util.Random;

public class GameService {

	public static int chooseDifficulty() {

		IO.println("\n===== SELECT DIFFICULTY =====");
		IO.println("1. Easy (15 Attempts)");
		IO.println("2. Medium (10 Attempts)");
		IO.println("3. Hard (5 Attempts)");

		int choice = Integer.parseInt(IO.readln("Choose Level: "));

		switch (choice) {

		case 1:
			return 15;

		case 2:
			return 10;

		case 3:
			return 5;

		default:
			return 10;
		}
	}

	public static void startGame() {

		int maxAttempts = chooseDifficulty();

		Game game = new Game(new Random().nextInt(100) + 1, maxAttempts);

		IO.println("\nGuess a Number Between 1 and 100");

		while (game.getAttempts() < game.getMaxAttempts()) {

			int guess = Integer.parseInt(IO.readln("Enter Guess: "));

			game.increaseAttempts();

			if (guess < game.getSecretNumber()) {

				IO.println("Too Low");

			} else if (guess > game.getSecretNumber()) {

				IO.println("Too High");

			} else {

				IO.println("\nCorrect Guess!");
				IO.println("Attempts Used : " + game.getAttempts());

				int score = (game.getMaxAttempts() - game.getAttempts() + 1) * 10;

				IO.println("Score : " + score);

				return;
			}

			IO.println("Remaining Attempts : " + (game.getMaxAttempts() - game.getAttempts()));
		}

		IO.println("\nGame Over!");
		IO.println("Correct Number Was : " + game.getSecretNumber());
	}

	public static void playAgain() {

		while (true) {

			startGame();

			String choice = IO.readln("\nPlay Again (Y/N): ");

			if (!choice.equalsIgnoreCase("Y")) {

				IO.println("Thank You For Playing!");
				break;
			}
		}
	}
}