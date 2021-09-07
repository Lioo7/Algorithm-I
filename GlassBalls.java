package Algorithms1;

import java.util.Arrays;

/**
 * Problem description: I am in a 'k'-story building.
 * I have with me 'n' glass balls.
 * I know that if I throw the ball out of the window, it will not break if the floor number is less than X,
 * and it will always breaks if the floor number is equal to or greater than X.
 * Assuming that I can reuse the balls which don't break, find X in the minimum number of throws
 * 
 * This class contains three functions:
 * 1. twoGlassBalls - 2 balls and k floors.
 * 2. threeGlassBalls - 3 balls and k floors.
 * 3. nGlassBalls - n balls and k floors.
 */

/* The function gets k floors and returns the minimum number of attemts
needed in worst case to finds the first floor that could break the ball(using two balls)
 * Implementation: Dynamic programming 
 * Time Complexity:
 */
public class GlassBalls {

	public static int twoGlassBalls(int k){
		if(k == 0 || k == 1 || k == 2) return k;

		/* Creates an array of which represents the minimum
	       number of attempts that needed for 2 balls and k floors. */
		int[] ballFloor = new int[k+1];

		for (int i = 0; i <= 2; i++) ballFloor[i] = i;

		for (int i = 3; i <= k; i++) {
			int min = k;

			for (int j = 1; j <= i-1; j++) {
				int ans = Math.max(j, ballFloor[i-j]+1);
				if(ans < min) min = ans;
			}
			ballFloor[i] = min;
		}
		//		System.out.println(Arrays.toString(ballFloor));
		return ballFloor[k];
	}

	/* The function gets k floors and returns the minimum number of attemts
	needed in worst case to finds the first floor that could break the ball(using three balls)
	 * Implementation: Dynamic programming 
	 * Time Complexity:
	 */
	public static int threeGlassBalls(int k){
		if(k == 0 || k == 1 || k == 2) return k;
		if(k == 3) return 2;

		/* Creates an array of which represents the minimum
	       number of attempts that needed for 2 balls and k floors. */
		int[] f2 = new int[k+1];

		for (int i = 1; i <= k; i++) {
			f2[i] = twoGlassBalls(i);
		}
		
		/* Creates an array of which represents the minimum
	       number of attempts that needed for 3 balls and k floors. */
		int[] f3 = new int[k+1];
		
		for (int i = 0; i <= 3; i++) {
			if(i == 3) f3[i] = 2;
			else f3[i] = i;
		}

		for (int i = 4; i <= k; i++) {
			int min = k;

			for (int j = 1; j <= i-1; j++) {
				int ans = Math.max(f2[j-1]+1, f3[i-j]+1);
				if(ans < min) min = ans;
			}
			f3[i] = min;
		}

		//		System.out.println(Arrays.toString(f3));
		return f3[k];
	}


	/* The function gets n balls and k floors and returns the minimum number of attemts
	   needed in worst case to finds the first floor that could break the ball
	 * Implementation: Dynamic programming 
	 * Time Complexity: O(nk^2)
	 */
	public static int nGlassBalls(int n, int k) {
		/* Creates a 2D matrix of ballFloor[i][j] which represents the minimum
	       number of attempts that needed for i balls and j floors. */
		int[][] ballFloor = new int[n+1][k+1];
		int ans = 0;

		// We need 0 attempt for 0 floor and 1 attempt for 1 floors
		if(k == 0 || k == 1) return k;

		for (int i = 1; i <= n; i++)
		{
			ballFloor[i][0] = 0;
			ballFloor[i][1] = 1;
		}

		// We always need i attempts for one ball and i floors.
		if(n == 1) return k;

		for (int i = 1; i <= k; i++) {
			ballFloor[1][i] = i;
		}

		// Fills the matrix
		// Iterates from the second ball till the last(n) ball 
		for (int i = 2; i <= n; i++) {
			// Iterates from the second floor till the last(k) floor 
			for (int j = 2; j <= k; j++) {
				ballFloor[i][j] = Integer.MAX_VALUE;
				// Iterates from the first floor till the current(j) floor 
				for (int t = 1; t < j; t++) {
					/*
					 * Takes the maximum answer between case 1 and case 2 and adds one to ans
					 * case 1: The ball breaks after dropping from 't'th floor,
					 * then we only need to check for floors lower than j with remaining balls
					 * so the problem reduces to t-1 floors and i-1 balls
					 * case 2: The ball doesn’t break after dropping from the 't'th floor,
					 * then we only need to check for floors higher than t,
					 * so the problem reduces to j-t floors and i balls
					 */
					ans = Math.max(ballFloor[i-1][t-1], ballFloor[i][j-t]) + 1;
					if (ans < ballFloor[i][j]) {
						ballFloor[i][j] = ans;
					}
				}
			}
		}	
		// ballFloor[n][k] holds the result
		//		System.out.println(Arrays.deepToString(ballFloor).replace("], ", "]\n").replace("[[", "[").replace("]]", "]") + "\n");
		return ballFloor[n][k];
	}



	public static void main(String[] args) {
		int n = 3, k = 36;
		System.out.println("Minimum number of attempts in worst case with " + n + " balls and " + k
				+ " floors is: " + nGlassBalls(n, k));

		System.out.println("Minimum number of attempts in worst case with 2 balls and " + k
				+ " floors is: " + twoGlassBalls(k));

		System.out.println("Minimum number of attempts in worst case with 3 balls and " + k
				+ " floors is: " + threeGlassBalls(k));
	}

}
