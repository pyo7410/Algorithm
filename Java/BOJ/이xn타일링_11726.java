import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 이xn타일링_11726 {
	public static int n;
	public static int[] memo = new int[1001];
	public final static int MOD = 10007;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		memo[1] = 1;
		memo[2] = 2;
		for (int i = 3; i <= n; ++i) {
			memo[i] = memo[i - 1] % MOD + memo[i - 2] % MOD; 
		}
		
		System.out.println(memo[n] % MOD);
	}
}
