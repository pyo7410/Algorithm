package D3;

import java.util.Scanner;

public class 정삼각형분할놀이_3233 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; ++tc) {
			long A = sc.nextLong();
			long B = sc.nextLong();
			
			// 삼각형의 내부를 최대한 몇 개를 채울 수 있는지 구하는 문제이므로
			// 작은 삼각형의 넓이와 큰 삼각형의 넓이를 비교하면 최대 몇개를
			// 넣을 수 있는지 알 수 있다.
			// 원래는 A의 넓이 / B의 넓이
			// 즉, ((A * A) / 2) / ((B * B) / 2)이 된다.
			long answer = (A * A) / (B * B);
			
			System.out.println("#" + tc + " " + answer);
		}
		
		sc.close();
	}
}
