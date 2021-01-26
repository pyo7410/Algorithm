import java.util.Scanner;

public class 수도요금경쟁_1284 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; ++tc) {
			int P, Q, R, S, W;
			P = sc.nextInt();
			Q = sc.nextInt();
			R = sc.nextInt();
			S = sc.nextInt();
			W = sc.nextInt();
			
			int a_corp = W * P;
			int b_corp;
			
			if (W <= R) {
				b_corp = Q;
			} else {
				b_corp = ((W - R) * S) + Q;
			}
			
			System.out.println("#" + tc + " " + Math.min(a_corp, b_corp));
		}
		
		sc.close();
	}
}
