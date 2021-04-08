import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 쉬운계단수_10844 {
	public final static long mod = 1000000000;
	public static int N;
	public static long[][] memo = new long[101][10];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= 9; ++i) {
			memo[1][i] = 1;
		}
		
		for (int i = 2; i <= N; ++i) {
			for (int j = 0; j <= 9; ++j) {
				if (j - 1 >= 0) {
					memo[i][j] += memo[i - 1][j - 1];
				}
				
				if (j + 1 <= 9) {
					memo[i][j] += memo[i - 1][j + 1];
				}
				
				memo[i][j] %= mod;
			}
		}
		
		long answer = 0;
		for (int i = 0; i <= 9; ++i) {
			answer += memo[N][i];
		}
		
		System.out.println(answer);
	}
}
