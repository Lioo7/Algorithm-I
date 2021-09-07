package Algorithms1;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * Two algorithms search for 2 largest elements of an array. 
   Each function returns the number of comparisons
 *
 */

public class MaxMax {
	/*
	 * The function maxmax3 
	 * searches two largest elements of the an array
	 * the first check is: a[i]>max1 
	 * @param arr - the array
	 * @return variable count the number of comparisons
	 * 
	 * Complexity: (3n/2)-2 comparisons 
	 */
	public static int[]maxMax3(int[] arr){
		int max1, max2;
		int count = 0;

		count++;
		if(arr[0]>arr[1]) {
			max1 = arr[0];
			max2 = arr[1];
		}
		else {
			max1 = arr[1];
			max2 = arr[0];
		}

		for (int i = 2; i < arr.length-1; i+=2) {
			count++;
			if(arr[i]>arr[i+1]) {
				count++;
				if(arr[i]>max1) {
					max2 = max1;
					max1 = arr[i];
				}
				else {
					count++;
					if(arr[i]>max2) {
						max2 = arr[i];
					}
				}
			}
			else {
				count++;
				if(arr[i+1]>max1) {
					max2 = max1;
					max1 = arr[i+1];
				}
				else {
					count++;
					if(arr[i+1]>max2) {
						max2 = arr[i+1];
					}
				}
			}
		}

		if (arr.length%2 != 0){
			count++;
			if (arr[arr.length-1] > max1){
				max2 = max1;
				max1 = arr[arr.length-1];
			}
			else {
				if (arr[arr.length-1] > max2) max2 = arr[arr.length-1];
				count++;
			}
		}

		int res[] = {max1, max2, count};
		return res;
	}


	public class Node {
		private int score;
		private Stack<Integer> stack;
		private boolean lost;

		public Node(int score) {
			this.score = score;
			this.stack = new Stack<Integer>(); 
			this.lost = false;
		}


		public boolean isLost() {
			return lost;
		}


		public void setLost(boolean lost) {
			this.lost = lost;
		}

		public String toString(){
			return "" + score;
		}
	}

	/*
	 * The function maxmax4 searches two largest elements
	 * of the an array using stack 
	 * @param arr - the array
	 * @return variable count the number of comparisons
	 * 
	 * Complexity: n-1[for max1] + log(n)-1[for max2] comparisons
	 */
	public void maxMax4(int[] arr){
		int count = 0;
		int size = arr.length, len = size;
		Node a[] = new Node[size], t[] = new Node[size];

		for (int i=0; i<size; i++){
			a[i] = new Node(arr[i]);
		}
		int i=0, j=0;
		while(size>1){
			count++;
			if (a[i].score > a[i+1].score){
				t[j] = a[i];
				t[j].stack.push(a[i+1].score);
			}
			else{
				t[j] = a[i+1];
				t[j].stack.push(a[i].score);
			}
			i = i + 2;
			j = j + 1;
			count++;
			if (i==len){
				size = len/2;
				len = size;
				a = t;
				t = new Node[size];
				i = j = 0;
			}
			else{ 
				count++;
				if (i==len-1){
					size = len/2+1;
					len = size;
					t[j] = a[i];
					a = t;
					t = new Node[size];
					i = j = 0;
				}
			}
		}
		int max1 = a[0].score;
		int max2 = a[0].stack.pop();
		while (!a[0].stack.empty()){
			int x = a[0].stack.pop();
			if (x > max2) max2 = x;
		}
		System.out.println("max1 = "+max1 + ", max2 = "+max2);
		System.out.println("comparisons = "+count);
	}

	public static void main(String[] args) {
		Random random = new Random();
		int avg = 0;
		int iterations = 1;
		int size = 27;
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = random.nextInt(size);
		}

		int[] newArr = maxMax3(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("max1: " + newArr[0]);
		System.out.println("max2: " + newArr[1]);
		System.out.println("Number of comparisons: " + newArr[2]);

		//		for (int i = 0; i < iterations; i++) {
		//			avg = maxMax3(arr)[2];
		//		}
		//		avg = avg/iterations;
		//		System.out.println("Number of comparisons: " + avg);
	}

}
