package Algorithms1;

public class Power {

	public static void main(String[] args) {
		System.out.println(Pow_Ind(3,4));
		System.out.println(Pow_Rec(3,4));
		System.out.println(Pow_Bin(3,4));
		System.out.println(Pow_Bin_Rec(1,3,4));
	}

	private static int Pow_Ind(int x, int n) {
		int ans = 1;
		for (int i = 0; i < n; i++) {
			ans = ans*x;
		}
		return ans;
	}

	private static int Pow_Rec(int x, int n) {
		if (n==1)
			return x;
		return x*Pow_Rec(x,n-1);

	}
	private static int Pow_Bin(int x, int n) {
		int ans = 1;
		while(n > 0)
		{
			if (n%2 == 1)
			{
				ans = ans*x;
			}
			x = x*x;
			n = n/2;
		}
		return ans;
	}

	private static int Pow_Bin_Rec(int ans, int x, int n) {
		if (n == 0)
			return ans;
		if (n%2 == 1)
			ans = ans*x;
		return (Pow_Bin_Rec(ans, x*x, n/2));
	}
}
