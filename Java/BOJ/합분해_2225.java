import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 합분해_2225 {
	public static final long mod = 1000000000L;
	public static int N, K;
	public static long[][] memo = new long[201][201];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		memo[0][0] = 1L;
		
		for (int i = 1; i <= K; ++i) {
			for (int j = 0; j <= N; ++j) {
				for (int l = 0; l <= j; ++l) {
					memo[i][j] += memo[i - 1][j - l];
					memo[i][j] %= mod;
				}
			}
		}
		
		System.out.println(memo[K][N]);
	}
}
