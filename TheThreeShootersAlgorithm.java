package Algorithms1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Problem description: There are three shooters: shooter A, B and C. The rules
 * are the following: Each one has a different chance to hit:
 * Shooter A will hit for 100% of the times.
 * Shooter B will hit for 80% of the times.
 * And shooter C will hit for 50% of the times.
 */
public class TheThreeShootersAlgorithm {

	public static char treeShoters() {
		char winner = 'K';
		// Raffles off which player will start first, second and third.
		Random rand = new Random();
		List<Character> playersList = new ArrayList<Character>();
		/*
		 * Turn: 1 will always mean it's A's turn. 2 will always mean it's B's turn. 3
		 * will always mean it's C's turn.
		 */
		while (playersList.size() != 3) {
			int random = rand.nextInt(3);
			char c = (char) (random + 'A');
			if (!playersList.contains(c)) {
				playersList.add(c);
			}
		}

		boolean hit;
		int round = 0; // Tha actual round for the calculations.
		int printRound = 1; // The round that the program will print.
		int numOfPlayers = 3;
//		System.out.println("\nplayers list: " + playersList.toString());

		// Keep running while there are more then one player alive.
		while (numOfPlayers > 1) {
			char turn = playersList.get(round%3);
			// Checks if the player is dead already, if yes then the turn goes to the next player
			if(turn == 'K') {
				round++;
				turn = playersList.get(round%3);
//				System.out.println("\nRound: " + printRound + ", player " + turn + " turn");
			}
//			System.out.println("\nRound: " + printRound + ", player " + turn + " turn");

			switch (turn) {
			case 'A': // Player A's turn.
				if (playersList.contains('B')) {
					// Player A shooting player B (100% accuracy).
//					System.out.println("Player A shoots player B");
					// Player B died.
					playersList.set(playersList.indexOf('B'), 'K');
//					System.out.println("Player A kiiled player B");
					numOfPlayers--;
				} else {
					// Player A shooting player B (100% accuracy).
					// Player C died.
					playersList.set(playersList.indexOf('C'), 'K');
//					System.out.println("Player A kiiled player C");
					numOfPlayers--;
				}
				break;

			case 'B': // Player B's turn.
				if (playersList.contains('A')) {
					// Player B shooting player A (80% accuracy).
//					System.out.println("Player B shoots player A");
					hit = shoot(80);
					if (hit == true) {
						// Player A died.
						playersList.set(playersList.indexOf('A'), 'K');
//						System.out.println("Player B kiiled player A");
						numOfPlayers--;
					} else {
//						System.out.println("Player B missed player A");
					}
				} else {
					// Player B shooting player C (80% accuracy).
//					System.out.println("Player B shots player C");
					hit = shoot(80);
					if (hit == true) {
//						// Player C died.
						playersList.set(playersList.indexOf('C'), 'K');
//						System.out.println("Player B killed player C");
						numOfPlayers--;
					} else {
//						System.out.println("Player B missed player C");
					}
				}
				break;

			case 'C': // Player C's turn.
				if (numOfPlayers == 3) {
//					System.out.println("Player C shoots in the air");
				} else {
					if (playersList.contains('A')) {
						// Player C shooting player A (50% accuracy).
//						System.out.println("Player C shoots player A");
						hit = shoot(50);
						if (hit == true) {
//							// Player A died.
							playersList.set(playersList.indexOf('A'), 'K');
//							System.out.println("Player C kiiled player A");
							numOfPlayers--;
						} else {
//							System.out.println("Player C missed player A");
						}
					} else {
						// Player C shooting player B (50% accuracy).
//						System.out.println("Player C shoots player B");
						hit = shoot(50);
						if (hit == true) {
//							// Player B died.
							playersList.set(playersList.indexOf('B'), 'K');
//							System.out.println("Player C kiiled player B");
							numOfPlayers--;
						} else {
//							System.out.println("Player C missed player B");
						}
					}
				}
				break;
			}
			round++; // The turn goes to the next player.
			printRound++;
		}
		for (int i = 0; i < playersList.size(); i++) {
			if(playersList.get(i) != 'K') {
				winner = playersList.get(i);
				break;
			}
		}
//		System.out.println("\nThe winner in the game is: player " + winner);
		return winner;
	}

	// This method gets the shoot accouracy of the players and return if he hit the
	// target.
	private static boolean shoot(int accouracy) {
		boolean ans = false;
		Random rand = new Random();
		int random = rand.nextInt(100) + 1;
		if (random <= accouracy) {
			ans = true;
		}

		return ans;
	}

	/*
	 * This method gets the amount of iterations and returns the array that
	 * contains the odds of which one of the players to win.
	 */
	private static double[] simulation(int iterations) {
		// This array will contain the odds of which one of the players to win.
		double oddsArr[] = new double[3];
		/*
		 * This array contains the amount of victories of all players. results[0] =
		 * Player A. results[1] = Player B. results[2] = Player B.
		 */
		double victories[] = new double[3];

		for (int i = 0; i < iterations; i++) {
			// Runs a game.
			char winner = treeShoters();
			// Converts the wiiner player char to a number between [0,2].
			int index = winner - 65;
			victories[index]++;
		}

		for (int i = 0; i < oddsArr.length; i++) {
			oddsArr[i] = (victories[i] / iterations) * 100;
		}

		return oddsArr;
	}

	public static void main(String[] args) {
//		treeShoters();
		// Number of iterations.
		int iterations = 5000000;
		double result[] = simulation(iterations);
		double playerA = result[0];
		double playerB = result[1];
		double playerC = result[2];
		
		System.out.println("\n----------------------------------------");
		System.out.println("*Three shooters simulation*");
		System.out.println("Number of iterations: " + iterations);
		System.out.println("----------------------------------------");
		System.out.printf("The chance of player A to win is: " + "%.2f" + "%%", playerA);
		System.out.printf("\nThe chance of player B to win is: " + "%.2f" + "%%", playerB);
		System.out.printf("\nThe chance of player C to win is: " + "%.2f" + "%%", playerC);
	}

}
