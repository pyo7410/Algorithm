import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Zero1타일_1904 {
	public final static int mod = 15746;
	public static int N;
	public static int[] memo = new int[1000001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		memo[1] = 1;
		memo[2] = 2;
		for (int i = 3; i <= N; ++i) {
			memo[i] = memo[i - 1] + memo[i - 2];
			memo[i] %= mod;
		}
		
		System.out.println(memo[N] % mod);
	}
}
