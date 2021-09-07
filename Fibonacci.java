package Algorithms1;

public class Fibonacci {
	
	public static void main(String[] args) {
		int num = 10;
		int fibb = Fibb(num);//O(num)
		System.out.println(fibb);
		
		int fibb2 = Fibb_With_Pow_Bin(num);//O(log(num))
		System.out.println(fibb2);


	}


	private static int Fibb(int num) {
		if (num == 1)
			return 1;
		if (num == 2)
			return 1;
		
		int[][] mat = {{1,1},{1,0}};
		int[][] ans = {{1,1},{1,0}};
		
		
		for (int i = 0; i < num-2; i++) {
			ans = Multiply(ans,mat);
		}
		
		return ans[0][0];

	}

	private static int[][] Multiply(int[][] mat, int[][] mat2) {
		int[][] ans = new int[2][2];
		ans[0][0] = mat[0][0]*mat2[0][0] + mat[0][1]*mat2[1][0];
		ans[0][1] = mat[0][0]*mat2[0][1] + mat[0][1]*mat2[1][1];
		ans[1][0] = mat[1][0]*mat2[0][0] + mat[1][1]*mat2[1][0];
		ans[1][1] = mat[1][0]*mat2[0][1] + mat[1][1]*mat2[1][1];

		return ans;
	}
	
	private static int Fibb_With_Pow_Bin(int num) {
		if (num == 1)
			return 1;
		if (num == 2)
			return 1;
		int[][] mat = {{1,1},{1,0}};
		int[][] ans = Pow_Bin(mat, num-2);
		
		return ans[0][0];
	}

	private static int[][] Pow_Bin(int[][] mat, int n) {
		int[][] ans = {{1,1},{1,0}};
		while(n > 0)
		{
			if (n%2 == 1)
			{
				ans = Multiply(ans,mat);
			}
			mat = Multiply(mat,mat);
			n = n/2;
		}
		return ans;
	}
}