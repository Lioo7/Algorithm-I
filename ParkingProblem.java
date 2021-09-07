package Algorithms1;

public class ParkingProblem {
	/*
	 * The function parking gets an array of cars and return the number of cars
	 * @param arr - the array
	 * @return variable count the number of cars at the parking 
	 * 
	 * Complexity: n(n+1) = O(n^2)
	 */
	public static int parking(int[] arr) {
		int len = arr.length;
		int count = 0;
		int ans = 0;
		int i = (int)(Math.random()*len);
		int sign1 = 1;
		int sign2 = 2;
		boolean running = true; 

		arr[i] = sign1; // Marks the first car that sees
		while(running) {	
			i = (i+1) % len; // Moves to the next car in the circle 
			count++; // Counts the car
			
			// Checks if the car has the first sign 
			if(arr[i] == sign1) {
				arr[i] = sign2; // If yes, change the sign to the second sign 
				ans = count;

				// Goes back to the first car that he saw 
				int diff = i-count;
				if(diff < 0) diff += len;
				i = (diff)%len;
				
				if(arr[i] == sign2) { // If that car has the second sign, we done 
					running = false;
				}
				else { // Otherwise, reset count and ans and continue 
					count = 0;
					ans = 0;
				}
			}
		}

		return ans;
	}
	
	

	public static void main(String[] args) {
		//		int[] arr = new int[10];
		//		for (int i = 0; i < arr.length; i++) {
		//			arr[i] = (int)(Math.random() * 30);
		//		}
		int[] arr = {0, 0, 0, 0, 1, 0, 0, 1, 0, 0};
		System.out.println(parking(arr));
	}

}
