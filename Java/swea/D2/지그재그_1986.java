import java.util.Scanner;

public class 지그재그_1986 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int n = sc.nextInt();
			
			int result = 0;
			for (int i = 1; i <= n; ++i) {
				if (i % 2 == 0) {
					result -= i;
				} else {
					result += i;
				}
			}
			
			System.out.println("#" + tc + " " + result);
		}
		
		sc.close();
	}
}
