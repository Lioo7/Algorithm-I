package Algorithms1;

import java.util.Arrays;

public class SecretaryProblem {
	
	public static int[] secretarySort(int[] arr) {
		int[] arr2 = sub1(arr);
		int[] arr3 = sub2(arr);
		if(sum(arr)<sum(arr2) && sum(arr)<sum(arr3)) {
			System.out.println(arr);
			return arr;
		}
		else if(sum(arr2)<sum(arr3)){
			return secretarySort(arr2);
		}
		else {
			return secretarySort(arr3);
		}
	}
	
	public static int[] sub1(int[] arr) {
		int[] newArr = new int[arr.length];
		newArr[0] = arr[1];
		newArr[1] = arr[0];
		newArr[2] = arr[2];
		return newArr;
	}
	
	public static int[] sub2(int[] arr) {
		int[] newArr = new int[arr.length];
		newArr[0] = arr[0];
		newArr[1] = arr[2];
		newArr[2] = arr[1];
		return newArr;
	}
	
	public static int sum(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += sum + arr[i];
		}
		return sum;	
	}

	public static void main(String[] args) {
		int[] arr = {10, 8, 1};
		System.out.println(Arrays.toString(secretarySort(arr)));
	}

}
