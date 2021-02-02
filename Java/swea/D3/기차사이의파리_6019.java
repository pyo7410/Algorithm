package D3;

import java.util.Scanner;

public class 기차사이의파리_6019 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; ++tc) {
			int D = sc.nextInt();
			double A = sc.nextDouble();
			double B = sc.nextDouble();
			double F = sc.nextDouble();
			
			// 기차가 충돌하는데 걸리는 시간은
			// 마주보고 달려오고 있으므로 총 거리 / (A의 속도 + B의 속도)가된다
			double crash_time = D / (A + B);
			
			// 파리가 움직인 거리는 B의 전면부에 닿아 방향을 바꿔도 결국 계속 움직이는 것 이므로
			// 시간동안 움직인 거리를 구하면  같은 의미가 되므로 거리 = 속력 * 시간이 된다.
			System.out.printf("#%d %.6f\n", tc, crash_time * F);
		}
		
		sc.close();
	}
}
