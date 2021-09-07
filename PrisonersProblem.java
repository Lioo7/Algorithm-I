package Algorithms1;
import java.util.Arrays;
import java.util.Random;

/**
 * Problem description: https://math.stackexchange.com/questions/116340/100-prisoners-and-a-lightbulb
 */

public class PrisonersProblem {
	
	public static void lightIsOn(int n) {
		boolean light = true;
		int count = 1;
		int visits = 0;
		/* Initialzie an array of n prsioners,
		 * each prisioner has a boolean value:
		 * False(initialze by default) - the prisioner does not turn off the light
		 * True - the prisioner turn on the light already
		 * Prisioner number zero always be the one that count
		 */
		boolean[] prisoners = new boolean[n];
		// Create instance of Random class 
		Random rand = new Random(); 

		while(count != n) {
			// Generate random integers in range 0 to n+1 
			int currentPrisioner = rand.nextInt(n); 
			// Checks if the current prisioner is the one that count
			if(currentPrisioner == 0) {
				// If the light is off, the prisioner turns it on and count
				if(light == false) {
					count++;
					light = true;
				}
			}
			/*
			 * If the light is on and the prisioner did not turned it off already,
			 * then the prisioner turns off the light
			 */
			else if (light == true && !prisoners[currentPrisioner]){
				light = false;
				prisoners[currentPrisioner] = true;
			}
			visits++;
		}
		System.out.println("count: " + count);
		System.out.println(Arrays.toString(prisoners));
		System.out.println("number of visits: " + visits);
	}	
	
	
	public static void lightIsUnknown(int n) {
		boolean light;
		// Create instance of Random class 
		Random rand = new Random(); 
		// Raffles the light's mood
		int lightStatus = rand.nextInt(2); 
		if(lightStatus == 0) {
			 light = false;
		}
		else {
			light = true;
		}
		
		System.out.println("light mood: " + light);
		
		int count = 2;
		int visits = 0;
		/* Initialzie an array of n prsioners,
		 * each prisioner has a integer value that represent the amount of times 
		 * that the the prisioner turned the light on
		 * Prisioner number zero always be the one that count
		 */
		int[] prisoners = new int[n];

		while(count < n*2) {
			// Generate random integers in range 0 to n+1 
			int currentPrisioner = rand.nextInt(n); 
			// Checks if the current prisioner is the one that count
			if(currentPrisioner == 0) {
				// If the light is off, the prisioner turns it on and count
				if(light == false) {
					count++;
					light = true;
				}
			}
			/*
			 * If the light is on and the prisioner did not turned it off already,
			 * then the prisioner turns off the light
			 */
			else if (light == true && prisoners[currentPrisioner]<2){
				light = false;
				prisoners[currentPrisioner]++;
			}
			visits++;
		}
		System.out.println("count: " + count);
		System.out.println(Arrays.toString(prisoners));
		System.out.println("number of visits: " + visits);
	}	

	public static void main(String[] args) {
		System.out.println("lightIsOn:");
		lightIsOn(100);
		System.out.println("\nlightIsUnknown:");
		lightIsUnknown(100);
	}

}
