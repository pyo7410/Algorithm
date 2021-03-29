package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GCD_8659 {
	public static int K;
	// 90까지 가면 매우 큰 숫자가 되어 int형 범위를 벗어난다.
	public static long[][] memo = new long[91][2];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		memo[1][0] = 2;
		memo[1][1] = 1;
		for (int i = 2; i <= 90; ++i) {
			memo[i][0] = memo[i - 1][0] + memo[i - 1][1];
			memo[i][1] = memo[i - 1][0];
		}

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			K = Integer.parseInt(br.readLine());
			
			sb.append("#").append(tc).append(" ")
			  .append(memo[K][0]).append(" ").append(memo[K][1])
			  .append("\n");
		}
		
		System.out.println(sb);
	}
}
