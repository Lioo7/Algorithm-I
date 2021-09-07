package Algorithms1;

public class PizzaProblem {

	public static int[] pizza(int n) {
		int avi = 0; //2x
		int beni = 0; //x

		while(n>1) {
			avi++;
			n--;
			if(n>0) {
				beni++;
				n--;
			}
			if(n>0) {
				avi++;
				n--;
			}
		}
		int[] result = {avi, beni};
		return result;
	}

	public static void main(String[] args) {
		double bestDivision = 0;
		double bestAns = 0;
		for (int i = 1; i <= 100; i++) {
			double avi = pizza(i)[0];
//			System.out.println(avi/i);
			if(avi/i > bestAns) {
				bestAns = avi/i;
				bestDivision = i;
			}
		}
		System.out.println("The best division is: " + bestDivision);
	}

}
