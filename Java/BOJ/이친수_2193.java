import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 이친수_2193 {
	public static int N;
	public static int[] memo = new int[91];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		memo[1] = 1;
		memo[2] = 1;
		memo[3] = 2;
		for (int i = 4; i <= N; ++i) {
			memo[i] = memo[i - 1] + memo[i - 2];
		}
		
		System.out.println(memo[N]);
	}
}
