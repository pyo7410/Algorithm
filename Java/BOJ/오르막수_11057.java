import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 오르막수_11057 {
	public final static int mod = 10007;
	public static int N, answer;
	public static int[][] memo = new int[1001][10];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i <= 9; ++i) {
			memo[1][i] = 1;
		}
		
		for (int i = 2; i <= N; ++i) {
			for (int j = 0; j <= 9; ++j) {
				for (int k = j; k <= 9; ++k) {
					memo[i][j] += (memo[i - 1][k] % mod);
				}
			}
		}
		
		for (int i = 0; i <= 9; ++i) {
			answer += (memo[N][i] % mod);
		}
		
		System.out.println(answer % mod);
	}
}
