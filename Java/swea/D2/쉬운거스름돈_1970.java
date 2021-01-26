import java.util.Scanner;

public class 쉬운거스름돈_1970 {
	public static int[] money = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int n = sc.nextInt();
			
			System.out.println("#" + tc);
			
			int changeCnt = 0;
			for (int i = 0; i < 8; ++i) {
				changeCnt = n / money[i];
				System.out.print(changeCnt + " ");
				
				n %= money[i];
			}
			
			System.out.println();
		}
		
		sc.close();
	}
}
