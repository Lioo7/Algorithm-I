package Algorithms1;

import java.util.Arrays;

public class LIS {
	/*
	 * Complexity[for the path]: O(nlogn)[sort] + O(n*n) = O(n^2 + nlogn) = O(n^2)
	 * Complexity[for the length]: O(nlogn)
	 */

	public static int lisSize(int[] arr) {
		int len = arr.length;
		int[] lis = new int[len];
		lis[0] = arr[0];
		int lisSize = 1;

		for (int i = 0; i < len; i++) {
			// Searching the index of the number in the new array 
			int index = Arrays.binarySearch(lis, 0, lisSize, arr[i]);
			// Adjust the index to the position of the number
			if(index < 0) index = -index-1; 
			// Increments the index so the number will be placed at the last poition
			if(index == lisSize) lisSize++; 
			// Copies the number from the original array to the new one 
			lis[index] = arr[i];
		}
		System.out.println(Arrays.toString(lis));
		return lisSize;
	}

	public static int[] lisPath(int[] arr) {
		int len = arr.length;
		int[] lis = new int[len];
		int[][] matrix = new int[len][len];
		lis[0] = matrix[0][0] = arr[0];
		int lisSize = 1;

		for (int i = 0; i < len; i++) {
			// Searching the index of the number in the new array 
			int index = Arrays.binarySearch(lis, 0, lisSize, arr[i]);
			// Adjust the index to the position of the number
			if(index < 0) index = -index-1;
			// Increments the index so the number will be placed at the last poition
			if(index == lisSize) lisSize++;
			lis[index] = matrix[index][index] = arr[i];
			for (int j = 0; j < index; j++) {
				matrix[index][j] = matrix[index-1][j];
			}
		}
		System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n").replace("[[", "[").replace("]]", "]") + "\n");
		return Arrays.copyOf(matrix[lisSize-1], lisSize);
	}


	public static void main(String[] args) {
		int[] arr = {5, 2, 9, 7, 8, 5, 3, 2, 1, 10, 6};
//		System.out.println(lisSize(arr));
		System.out.println(Arrays.toString(lisPath(arr)));
	}

}
