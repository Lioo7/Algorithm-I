package Algorithms1;

class Node {
	int x;
	int y;
	int value;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.value = 0;
	}
}

public class AirPlaneAlgorithm {
	// Compleity: O(n*m)
	public static int airPlaneMinValue(Node[][] matrix) {
		int n = matrix.length; // Rows 
		int m = matrix[0].length; // Columns

		// Fills the start point
		matrix[0][0].value = 0;
		// Fills the rows with 0
		for (int i = 1; i < n; i++) {
			matrix[i][0].value = matrix[i-1][0].value + matrix[i-1][0].y;
		}
		// Fills the columns with 0
		for (int j = 1; j < n; j++) {
			matrix[0][j].value = matrix[0][j-1].value + matrix[0][j-1].x;
		}
		// Fills the rest of the matrix 
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				int left = matrix[i][j-1].value + matrix[i][j-1].x;
				int up = matrix[i-1][j].value + matrix[i-1][j].y;
				matrix[i][j].value = Math.min(left, up);
			}
		}

		return matrix[n-1][m-1].value;
	}

		public static String airPlanePath(Node[][] matrix) {
			String path = ""; // 1-up, 0-right 
			int i = matrix.length-1;
			int j = matrix[0].length-1;
	
			while(i!=0 && j!=0) {
				int left = matrix[i][j-1].value + matrix[i][j-1].x;
				int up = matrix[i-1][j].value + matrix[i-1][j].y;
				if(left > up) {
					path = "1" + path;
					i--;
				}
				else {
					path = "0" + path;
					j--;
				}
			}
	
			while(i!=0 ) {
				path = "1" + path;
				i--;
			}
	
			while(j!=0 ) {
				path = "0" + path;
				j--;
			}
			
			return path;
		}


	public static void main(String[] args) {
		Node[][] matrix = {
				{new Node(1,5),new Node(4,1),new Node(0,6)},
				{new Node(4,7),new Node(2,5),new Node(0,3)},
				{new Node(1,0),new Node(2,0),new Node(0,0)}};

		System.out.println(airPlaneMinValue(matrix));
		System.out.println(airPlanePath(matrix));
	}
}
