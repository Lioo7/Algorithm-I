package Algorithms1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class LCS {
	// Complexity: O((m+1)*(n+1)) = O(m*n)
	public static String lcsDynamic(String s1, String s2) {
		int n = s1.length() + 1;
		int m = s2.length() + 1;
		// Creates a matrix n*m that will contain the result 
		int[][] matrix = new int[n][m];
		
		// Fills the first column by zero 
		for (int i = 0; i < n; i++) {
			matrix[i][0] = 0;
		}
		// Fills the first line by zero 
		for (int j = 0; j < m; j++) {
			matrix[0][j] = 0;
		}
		// Fills the rest of the matrix
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					matrix[i][j] = matrix[i-1][j-1] + 1;
				}
				else {
					matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
				}
			}
		}
		
		String ans = "";
		int i = n-1;
		int j = m-1;
		while(matrix[i][j] != 0) {
			if(s1.charAt(i-1) == s2.charAt(j-1)) {
				ans = s1.charAt(i-1) + ans;
				i--;
				j--;
			}
			else if(matrix[i][j-1] > matrix[i-1][j]) {
				j--;
			}
			else {
				i--;
			}
		}
		
		return ans;
	}

	// Complexity: max of {O(n*[2^(n)]), O(m*[2^(m)])} 
	public static String lcsCompleteSearch(String x, String y) {
		// |x| = m, |Y| = n
		String maxSub = "null";
		int xLength = x.length(); // O(m)
		int yLength = y.length(); // O(n)
		int subX = (int) Math.pow(2, xLength);
		int subY = (int) Math.pow(2, yLength);
		String arrX[] = new String[subX];
		String arrY[] = new String[subY];	
		String arrSX[] = new String[subX];
		String arrSY[] = new String[subY];

		for (int i = 0; i < subX; i++) { // O([(2^m)*m])
			arrX[i] = "";
			int length = xLength;
			while(length>0) {
				arrX[i] += "0";
				length--;
			}
		}

		arrSX[0] = "";
		for (int i = 1; i < subX; i++) { // O(2^m)
			arrX[i] = plusOne(arrX[i-1]); // O(2^m)
			arrSX[i] = binaryToString(arrX[i], x); // O(2^m)
		}

		for (int i = 0; i < subY; i++) { // O([(2^n)*n])
			arrY[i] = "";
			int length = yLength;
			while(length>0) {
				arrY[i] += "0";
				length--;
			}
		}

		arrSY[0] = "";
		for (int i = 1; i < subY; i++) { // O(2^n)
			arrY[i] = plusOne(arrY[i-1]); // O(2^n)
			arrSY[i] = binaryToString(arrY[i], y); // O(2^n)
		}

		String[] shortString;
		String[] longString;
		if(xLength < yLength) {
			shortString = arrSX;
			longString = arrSY;
		}
		else {
			shortString = arrSY;
			longString = arrSX;
		}
		int shortLength = shortString.length-1; // O(m)
		int longLength = longString.length-1; // O(n)

		sortStringArrayByLength(shortString); // O(mlogm)
		sortStringArrayByLength(longString); // O(nlogn)

		System.out.println(Arrays.toString(shortString));
		System.out.println(Arrays.toString(longString));

		for (int i = shortLength; i > 0; i--) { // O(2^m*2^n)=2^m+n
			for (int j = longLength; j > 0; j--) {
				if(shortString[i].equals(longString[j])) {
					return shortString[i];
				}
			}
		}

		return maxSub;
	}

	private static String binaryToString(String arrX, String str) {
		String ans = "";

		for (int i = 0; i < arrX.length(); i++) {
			if(arrX.charAt(i) == '1') {
				ans += str.charAt(i);
			}
		}

		return ans;
	}

	private static String plusOne(String arrX) {
		String bSub = arrX;
		int index = arrX.length()-1;
		while (index >= 0) {
			if(bSub.charAt(index) == '0') {
				bSub = bSub.substring(0, index) + '1' + bSub.substring(index + 1);
				break;
			}
			else { //if chatAt == 1
				bSub = bSub.substring(0, index) + '0' + bSub.substring(index + 1);
			}
			index--;
		}
		return bSub;
	}

	private static void sortStringArrayByLength(String[] stringArray) {
		Arrays.sort(stringArray, Comparator.comparing(String::length));
	}

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in); // object for scanner 
//		System.out.println("Enter first string: "); 
//		String x = sc.next(); 
//		System.out.println("Enter second string: "); 
//		String y = sc.next(); 
//		//		String x = "acctrjgccb";
//		//		String y = "bftkflgcca";
//		String result;
//
//		System.out.println("String X: " + x);
//		System.out.println("String Y: " + y);
//		result = lcsCompleteSearch(x, y);
//		System.out.println("The longest common substring is: " + result);
		
		System.out.println(lcsDynamic("adaswfsdgdbhq", "hpaghsfghwoq"));
	}
}
