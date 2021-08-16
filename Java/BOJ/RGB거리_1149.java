import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RGB거리_1149 {
	public static int N;
	// 1 : R, 2 : G, 3 : B
	public static int[][] memo = new int[1001][4];
	public static int[][] costs = new int[1001][4];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= 3; ++j) {
				costs[i][j] = Integer.parseInt(st.nextToken());				
			}
		}
		
		for (int i = 1; i <= N; ++i) {
			// 1 : R, 2 : G, 3 : B
			memo[i][1] = Math.min(memo[i - 1][2], memo[i - 1][3]) + costs[i][1];
			memo[i][2] = Math.min(memo[i - 1][1], memo[i - 1][3]) + costs[i][2];
			memo[i][3] = Math.min(memo[i - 1][1], memo[i - 1][2]) + costs[i][3];
		}
		
		Arrays.sort(memo[N]);
		
		System.out.println(memo[N][1]);
	}
}
