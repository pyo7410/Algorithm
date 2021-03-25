import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 일이삼더하기_9095 {
	public static int T, n;
	public final static int N = 12;
	public static int[] memo = new int[N];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		memo[1] = 1;
		memo[2] = 2;
		memo[3] = 4;
		for (int i = 4; i < N; ++i) {
			memo[i] = memo[i - 3] + memo[i - 2] + memo[i - 1];
		}
		
		for (int i = 0; i < T; ++i) {
			n = Integer.parseInt(br.readLine());
			System.out.println(memo[n]);
		}
	}
}
