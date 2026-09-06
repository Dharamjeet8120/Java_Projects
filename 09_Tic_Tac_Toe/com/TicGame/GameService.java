package com.TicGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameService {

	private static final Random random = new Random();

	private static int player1Wins = 0;
	private static int player2Wins = 0;
	private static int drawMatches = 0;

	private static final List<String> matchHistory = new ArrayList<>();

	public static void startGame() {

		while (true) {

			IO.println("\n==============================");
			IO.println("       TIC TAC TOE GAME");
			IO.println("==============================");

			IO.println("1. Single Player");
			IO.println("2. Multiplayer");
			IO.println("3. View Statistics");
			IO.println("4. Match History");
			IO.println("5. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1:
				startSinglePlayer();
				break;

			case 2:
				startMultiplayer();
				break;

			case 3:
				showStatistics();
				break;

			case 4:
				showMatchHistory();
				break;

			case 5:
				IO.println("Thank You For Playing!");
				return;

			default:
				IO.println("Invalid Choice!");
			}
		}
	}

	// ================= MULTIPLAYER =================

	private static void startMultiplayer() {

		String name1 = IO.readln("Enter Player 1 Name: ");

		String name2 = IO.readln("Enter Player 2 Name: ");

		Player player1 = new Player(name1, 'X');

		Player player2 = new Player(name2, 'O');

		int rounds = chooseRounds();

		playMatch(player1, player2, rounds);
	}

	// ================= SINGLE PLAYER =================

	private static void startSinglePlayer() {

		String name = IO.readln("Enter Your Name: ");

		Player player = new Player(name, 'X');

		Player computer = new Player("Computer", 'O');

		int rounds = chooseRounds();

		playMatch(player, computer, rounds);
	}

	// ================= ROUND SELECTION =================

	private static int chooseRounds() {

		IO.println("\n===== MATCH TYPE =====");
		IO.println("1. Best of 3");
		IO.println("2. Best of 5");

		int choice = Integer.parseInt(IO.readln("Enter Choice: "));

		if (choice == 2) {
			return 5;
		}

		return 3;
	}

	// ================= PLAY MATCH =================

	private static void playMatch(Player player1, Player player2, int totalRounds) {

		int requiredWins = (totalRounds / 2) + 1;

		int score1 = 0;
		int score2 = 0;

		IO.println("\nStarting Match...");
		IO.println(player1.getName() + " = X");

		IO.println(player2.getName() + " = O");

		for (int round = 1; round <= totalRounds; round++) {

			if (score1 == requiredWins || score2 == requiredWins) {

				break;
			}

			IO.println("\n==============================");
			IO.println("Round " + round);
			IO.println(player1.getName() + " : " + score1);

			IO.println(player2.getName() + " : " + score2);

			Player winner = playRound(player1, player2);

			if (winner == null) {

				drawMatches++;

				IO.println("Round Draw!");

			} else {

				winner.increaseWin();

				if (winner == player1) {

					score1++;

					player1Wins++;

				} else {

					score2++;

					player2Wins++;
				}

				IO.println(winner.getName() + " Won Round " + round);
			}
		}

		IO.println("\n==============================");
		IO.println("        FINAL SCORE");
		IO.println("==============================");

		IO.println(player1.getName() + " : " + score1);

		IO.println(player2.getName() + " : " + score2);

		if (score1 > score2) {

			IO.println("Winner : " + player1.getName());

			matchHistory.add(player1.getName() + " defeated " + player2.getName() + " (" + score1 + "-" + score2 + ")");

		} else if (score2 > score1) {

			IO.println("Winner : " + player2.getName());

			matchHistory.add(player2.getName() + " defeated " + player1.getName() + " (" + score2 + "-" + score1 + ")");

		} else {

			IO.println("Match Draw!");

			matchHistory.add(player1.getName() + " vs " + player2.getName() + " - Draw");
		}
	}

	// ================= PLAY ROUND =================

	private static Player playRound(Player player1, Player player2) {

		TicTacToe game = new TicTacToe();

		Player currentPlayer;

		if (random.nextBoolean()) {

			currentPlayer = player1;

		} else {

			currentPlayer = player2;
		}

		IO.println("\nFirst Turn : " + currentPlayer.getName());

		while (true) {

			displayBoard(game);

			int position;

			if (currentPlayer.getName().equals("Computer")) {

				position = computerMove(game);

				IO.println("Computer selected position: " + (position + 1));

			} else {

				position = Integer.parseInt(
						IO.readln(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") Position: ")) - 1;
			}

			if (!game.isValidMove(position)) {

				IO.println("Invalid Position!");
				continue;
			}

			game.makeMove(position, currentPlayer.getSymbol());

			if (game.checkWin(currentPlayer.getSymbol())) {

				displayBoard(game);

				return currentPlayer;
			}

			if (game.isDraw()) {

				displayBoard(game);

				return null;
			}

			if (currentPlayer == player1) {

				currentPlayer = player2;

			} else {

				currentPlayer = player1;
			}
		}
	}

	// ================= COMPUTER MOVE =================

	private static int computerMove(TicTacToe game) {

		char[] board = game.getBoard();

		// Try to win
		int winningMove = findWinningMove(game, 'O');

		if (winningMove != -1) {
			return winningMove;
		}

		// Block player
		int blockingMove = findWinningMove(game, 'X');

		if (blockingMove != -1) {
			return blockingMove;
		}

		// Center
		if (game.isValidMove(4)) {
			return 4;
		}

		// Random available position
		List<Integer> available = new ArrayList<>();

		for (int i = 0; i < board.length; i++) {

			if (game.isValidMove(i)) {
				available.add(i);
			}
		}

		return available.get(random.nextInt(available.size()));
	}

	// ================= FIND WINNING MOVE =================

	private static int findWinningMove(TicTacToe game, char symbol) {

		for (int i = 0; i < 9; i++) {

			if (!game.isValidMove(i)) {
				continue;
			}

			char[] board = game.getBoard();

			char oldValue = board[i];

			board[i] = symbol;

			boolean win = game.checkWin(symbol);

			board[i] = oldValue;

			if (win) {
				return i;
			}
		}

		return -1;
	}

	// ================= DISPLAY BOARD =================

	private static void displayBoard(TicTacToe game) {

		char[] b = game.getBoard();

		IO.println("");

		IO.println(" " + b[0] + " | " + b[1] + " | " + b[2]);

		IO.println("---+---+---");

		IO.println(" " + b[3] + " | " + b[4] + " | " + b[5]);

		IO.println("---+---+---");

		IO.println(" " + b[6] + " | " + b[7] + " | " + b[8]);
	}

	// ================= STATISTICS =================

	private static void showStatistics() {

		int totalMatches = player1Wins + player2Wins + drawMatches;

		IO.println("\n===== WINNING STATISTICS =====");

		IO.println("Total Round Results : " + totalMatches);

		IO.println("Player 1 Wins : " + player1Wins);

		IO.println("Player 2 Wins : " + player2Wins);

		IO.println("Draws : " + drawMatches);

		if (totalMatches > 0) {

			double player1Percentage = player1Wins * 100.0 / totalMatches;

			double player2Percentage = player2Wins * 100.0 / totalMatches;

			IO.println("Player 1 Win % : " + player1Percentage + "%");

			IO.println("Player 2 Win % : " + player2Percentage + "%");
		}
	}

	// ================= MATCH HISTORY =================

	private static void showMatchHistory() {

		IO.println("\n===== MATCH HISTORY =====");

		if (matchHistory.isEmpty()) {

			IO.println("No Match History Found!");

			return;
		}

		int count = 1;

		for (String match : matchHistory) {

			IO.println(count + ". " + match);

			count++;
		}
	}
}